import java.util.function.Function;

public class PipeLineControl {
    // IF_ID
    private int IF_ID_Write = 0x00000000;
    private int IF_ID_Read = 0x00000000;

    //ID_EX
    private int ID_EX_ReadReg1Value_Write = 0x00000000;
    private int ID_EX_ReadReg1Value_Read = 0x00000000;

    private int ID_EX_ReadReg2Value_Write = 0x00000000;
    private int ID_EX_ReadReg2Value_Read = 0x00000000;

    private int ID_EX_WriteReg1_Write = 0x00000000;
    private int ID_EX_WriteReg1_Read = 0x00000000;

    private int ID_EX_WriteReg2_Write = 0x00000000;
    private int ID_EX_WriteReg2_Read = 0x00000000;

    private int ID_EX_Function_Write = 0;
    private int ID_EX_Function_Read = 0;

    private int ID_EX_Opcode_Write = 0;
    private int ID_EX_Opcode_Read = 0;

    private int ID_EX_Control_Write = 0b000000000;
    private int ID_EX_Control_Read = 0b000000000;

    private short ID_EX_SEOffSet_Write = 0;
    private short ID_EX_SEOffSet_Read = 0;


    // EX_MEM
    private int EX_MEM_Control_Write = 0;
    private int EX_MEM_Control_Read = 0;;

    private int EX_MEM_SWValue_Write = 0;
    private int EX_MEM_SWValue_Read = 0;

    private int EX_MEM_CalcBTA_Write = 0;
    private int EX_MEM_CalcBTA_Read = 0;

    private int EX_MEM_Zero_Write = 0;
    private int EX_MEM_Zero_Read = 0;

    private int EX_MEM_ALUResult_Write = 0;
    private int EX_MEM_ALUResult_Read = 0;

    private int EX_MEM_WriteRegNum_Write = 0;
    private int EX_MEM_WriteRegNum_Read = 0;

    // MEM_WB

    private int MEM_WB_Control_Write = 0;
    private int MEM_WB_Control_Read = 0;

    private int MEM_WB_LWDataValue_Write = 0;
    private int MEM_WB_LWDataValue_Read = 0;

    private int MEM_WB_ALUResult_Write = 0;
    private int MEM_WB_ALUResult_Read = 0;

    private int MEM_WB_WriteRegNum_Write = 0;
    private int MEM_WB_WriteRegNum_Read = 0;


    public void IF_STAGE(int[] instructionArray, int instructionIndex)
    {
        IF_ID_Write = instructionArray[instructionIndex];
        if(IF_ID_Write == 0) {
//            System.out.println("IF_Stage: No-Op Detected");
            ID_EX_ReadReg1Value_Write = 0;
            ID_EX_ReadReg2Value_Write = 0;
            ID_EX_WriteReg1_Write = 0;
            ID_EX_WriteReg2_Write = 0;
            ID_EX_Function_Write = 0;
            ID_EX_Opcode_Write = 0;
        }
//        else
//            {System.out.println("IF_Stage: "+Integer.toHexString(IF_ID_Write));};
    }



    public void ID_STAGE(int[] Regs) {
        int Instruction = IF_ID_Read;

        if(IF_ID_Read != 0) {

            // Declare Register Ints and Function Name with every iteration so there's no carryover by mistake
            String FunctionOutput = new String();

            // get all relevant info from the instructions
            int opcode = Instruction >>> 26 & 0b111111;
            int function = Instruction & 0b111111;
            int rs = Instruction >>> 21 & 0b11111;
            int rt = Instruction >>> 16 & 0b11111;
            int rd = Instruction >>> 11 & 0b11111;
            int immInt = ((Instruction & 0xFFFF));
            short immShort = (short) immInt;
            // System.out.println(Integer.toHexString(opcode));

            ID_EX_ReadReg1Value_Write = Regs[rs];
            ID_EX_ReadReg2Value_Write = Regs[rt];
            ID_EX_WriteReg1_Write = rt;
            ID_EX_WriteReg2_Write = rd;
            ID_EX_Function_Write = function;
            ID_EX_Opcode_Write = opcode;
            ID_EX_SEOffSet_Write = immShort;

            FunctionOutput = "ID_Stage:";

            if (opcode == 0) {
                // R-type functions
                // RegDestination: 1, ALUSRC = 0, ALUOP = 10, MemRead = 0, MemWrite = 0, Branch = 0, MemToReg = 0, RegWrite = 1;
                ID_EX_Control_Write = 0b101000001;

                // switch statement to assign function names
                switch (function) {
                    case 0x20: // add
                        FunctionOutput = FunctionOutput + " add";
                        break;
                    case 0x22: // sub
                        FunctionOutput = FunctionOutput + " sub";
                        break;
                }
//                System.out.println(FunctionOutput + " $" + rd + " $" + rs + " $" + rt);


            } else {
                // break down the instruction and assign register values & imm value int/short

                switch (opcode) {
                    case 0x20: // lb
                        // RegDestination: 0, ALUSRC = 1, ALUOP = 00, MemRead = 1, MemWrite = 0, Branch = 0, MemToReg = 1, RegWrite = 1;
                        ID_EX_Control_Write = 0b010010011;
                        FunctionOutput = FunctionOutput + " lb";
//                        System.out.println(FunctionOutput + " $" + rt + ", " + immShort + "($" + rs + ")");
                        break;
                    case 0x28: // sb
                        // RegDestination: 0 (dont care), ALUSRC = 1, ALUOP = 00, MemRead = 0, MemWrite = 1, Branch = 0, MemToReg = 0, RegWrite = 0;
                        ID_EX_Control_Write = 0b010001000;
                        FunctionOutput = FunctionOutput + " sb";
//                        System.out.println(FunctionOutput + " $" + rt + ", " + immShort + "($" + rs + ")");
                        break;
                }
            }
        }

        else
            // no-ops
        {
//            System.out.println("ID_Stage: No-Op Detected");
        ID_EX_Control_Write = 0;
        return;}

    }

    public void EX_STAGE(int[] Regs, int[] MM) {

        if(ID_EX_Control_Read == 0)
        {
//            System.out.println("EX_Stage: No-Op Detected");
            EX_MEM_Control_Write = 0;
            EX_MEM_CalcBTA_Write = 0;
            EX_MEM_Zero_Write = 0;
            EX_MEM_ALUResult_Write = 0;
            EX_MEM_SWValue_Write = 0;
            EX_MEM_WriteRegNum_Write = 0;
        }


        EX_MEM_Control_Write = ID_EX_Control_Read & 0b11111;

        if (ID_EX_Opcode_Read == 0) {
            // switch statement to assign function names
            switch (ID_EX_Function_Read) {
                case 0x20: // add
//                    System.out.println("EX_Stage: $"+ID_EX_WriteReg2_Read+" = "+ID_EX_ReadReg1Value_Read+" + "+ID_EX_ReadReg2Value_Read);
                    EX_MEM_ALUResult_Write = ID_EX_ReadReg1Value_Read + ID_EX_ReadReg2Value_Read;
                    EX_MEM_WriteRegNum_Write = ID_EX_WriteReg2_Read;
                    EX_MEM_SWValue_Write = ID_EX_ReadReg2Value_Read & 0b11111111;

                    break;
                case 0x22: // sub
//                    System.out.println("EX_Stage: $"+ID_EX_WriteReg2_Read+" = "+ID_EX_ReadReg1Value_Read+" - "+ID_EX_ReadReg2Value_Read);
                    EX_MEM_ALUResult_Write = ID_EX_ReadReg1Value_Read - ID_EX_ReadReg2Value_Read;
                    EX_MEM_WriteRegNum_Write = ID_EX_WriteReg2_Read;
                    EX_MEM_SWValue_Write = ID_EX_ReadReg2Value_Read & 0b11111111;
                    break;
            }

        }

        else {
            switch (ID_EX_Opcode_Read) {
                case 0x20: // lb
//                    System.out.println("EX_Stage: $"+ID_EX_WriteReg1_Read+" = MM["+ID_EX_ReadReg1Value_Read+"+"+ID_EX_SEOffSet_Read+"]");
                    EX_MEM_ALUResult_Write = ID_EX_ReadReg1Value_Read+ID_EX_SEOffSet_Read; // need to add Sign Extended offset to MM Ref
                    EX_MEM_WriteRegNum_Write = ID_EX_WriteReg1_Read;
                    EX_MEM_SWValue_Write = ID_EX_ReadReg2Value_Read & 0b11111111;
                    break;
                case 0x28: // sb
//                    System.out.println("EX_Stage: MM["+ID_EX_ReadReg1Value_Read+"+"+ID_EX_SEOffSet_Read+"]"+" = $"+ID_EX_WriteReg1_Read);
                    EX_MEM_ALUResult_Write = ID_EX_ReadReg1Value_Read+ID_EX_SEOffSet_Read; // need to add Sign Extended offset to MM Ref
                    EX_MEM_SWValue_Write = ID_EX_ReadReg2Value_Read & 0b11111111;
                    // EX_MEM_WriteRegNum_Write = null; //invalid
                    break;
            }
        }
    }


    public void MEM_STAGE(int[] MM)
    {
        if(EX_MEM_Control_Read == 0) {
//            System.out.println("MEM_Stage: No-Op Detected");
            MEM_WB_Control_Write = 0;
            MEM_WB_ALUResult_Write = 0;
            MEM_WB_LWDataValue_Write = 0;
            MEM_WB_WriteRegNum_Write = 0;
        }

        else {

            MEM_WB_Control_Write = EX_MEM_Control_Read & 0b11;
            MEM_WB_ALUResult_Write = EX_MEM_ALUResult_Read;
            MEM_WB_WriteRegNum_Write = EX_MEM_WriteRegNum_Read;

            switch (EX_MEM_Control_Read) {
                case 0b01000: // sb
                case 0b01010:
                    MM[EX_MEM_ALUResult_Read] = EX_MEM_SWValue_Read;
//                    System.out.println("MEM_Stage: Save byte "+EX_MEM_SWValue_Read+" to MM" + EX_MEM_ALUResult_Read);
                    break;
                case 0b10011: // lb
                    MEM_WB_LWDataValue_Write = MM[EX_MEM_ALUResult_Read];
//                    System.out.println("MEM_Stage: Load byte from MM" + EX_MEM_ALUResult_Read + " to $" + EX_MEM_WriteRegNum_Read);
                    break;
                case 0b00001: // add/sub - do nothing
//                    System.out.println("MEM_Stage: No Memory Access - Do Nothing");
                    break;
            }
        }
    }

    public void WB_Stage(int[] Regs)
    {
        switch (MEM_WB_Control_Read) {
            case 0b11: // lb
                Regs[MEM_WB_WriteRegNum_Read] = MEM_WB_LWDataValue_Read;
//                System.out.println("Write to $"+MEM_WB_WriteRegNum_Read+" LWData: "+Integer.toHexString(MEM_WB_LWDataValue_Read));
                break;
            case 0b01: // add/sub
                Regs[MEM_WB_WriteRegNum_Read] = MEM_WB_ALUResult_Read;
//                System.out.println("Write ALU Result "+Integer.toHexString(MEM_WB_ALUResult_Read)+" to $"+MEM_WB_WriteRegNum_Read);
                break;
            case 0b00: // sb
//                System.out.println("WB Stage: No Register Writes - Do Nothing");
                break;
            case 0b10: // sb
//                System.out.println("WB Stage: No Register Writes - Do Nothing");
                // do nothing
                break;
                    }
    }


    public void Print_Out_Everything(int[] Regs, int RegCount)
    {
//         Registers
         for(int regsIndex = 0; regsIndex < RegCount; regsIndex++)
         {
         System.out.println("Register "+regsIndex+": "+Integer.toHexString(Regs[regsIndex]));
         }

        // PipeLine Registers
        // IF_ID
        System.out.println("__________");
        System.out.println("IF_ID_Write");
        System.out.println("Inst: "+Integer.toHexString(IF_ID_Write));

        System.out.println("IF_ID_Read");
        System.out.println("Inst: "+Integer.toHexString(IF_ID_Read));

        System.out.println("__________");
        System.out.println("ID_EX_Write");
        System.out.println("ID_EX_ReadReg1_Write: "+Integer.toHexString(ID_EX_ReadReg1Value_Write));
        System.out.println("ID_EX_ReadReg2_Write: "+Integer.toHexString(ID_EX_ReadReg2Value_Write));
        System.out.println("ID_EX_WriteReg1_Write: "+ID_EX_WriteReg1_Write);
        System.out.println("ID_EX_WriteReg2_Write: "+ID_EX_WriteReg2_Write);
        System.out.println("ID_EX_Function_Write: "+Integer.toHexString(ID_EX_Function_Write));
        System.out.println("ID_EX_Opcode_Write: "+Integer.toHexString(ID_EX_Opcode_Write));
        System.out.println("ID_EX_Control_Write: "+Integer.toBinaryString(ID_EX_Control_Write));


        System.out.println("ID_EX_Read");
        System.out.println("ID_EX_ReadReg1_Read: "+Integer.toHexString(ID_EX_ReadReg1Value_Read));
        System.out.println("ID_EX_ReadReg2_Read: "+Integer.toHexString(ID_EX_ReadReg2Value_Read));
        System.out.println("ID_EX_WriteReg1_Read: "+ID_EX_WriteReg1_Read);
        System.out.println("ID_EX_WriteReg2_Read: "+ID_EX_WriteReg2_Read);
        System.out.println("ID_EX_Function_Read: "+Integer.toHexString(ID_EX_Function_Read));
        System.out.println("ID_EX_Opcode_Read: "+Integer.toHexString(ID_EX_Opcode_Read));
        System.out.println("ID_EX_Control_Read: "+Integer.toBinaryString(ID_EX_Control_Read));

        System.out.println("__________");
        System.out.println("EX_MEM_Write");
        System.out.println("EX_MEM_Control_Write: "+Integer.toHexString(EX_MEM_Control_Write));
        System.out.println("EX_MEM_CalcBTA_Write: "+Integer.toHexString(EX_MEM_CalcBTA_Write));
        System.out.println("EX_MEM_Zero_Write: "+Integer.toHexString(EX_MEM_Zero_Write));
        System.out.println("EX_MEM_ALUResult_Write: "+Integer.toHexString(EX_MEM_ALUResult_Write));
        System.out.println("EX_MEM_SWValue_Write: "+Integer.toHexString(EX_MEM_SWValue_Write));
        System.out.println("EX_MEM_WriteRegNum_Write: "+EX_MEM_WriteRegNum_Write);


        System.out.println("EX_MEM_Read");
        System.out.println("EX_MEM_Control_Read: "+Integer.toHexString(EX_MEM_Control_Read));
        System.out.println("EX_MEM_CalcBTA_Read: "+Integer.toHexString(EX_MEM_CalcBTA_Read));
        System.out.println("EX_MEM_Zero_Read: "+Integer.toHexString(EX_MEM_Zero_Read));
        System.out.println("EX_MEM_ALUResult_Read: "+Integer.toHexString(EX_MEM_ALUResult_Read));
        System.out.println("EX_MEM_SWValue_Read: "+Integer.toHexString(EX_MEM_SWValue_Read));
        System.out.println("EX_MEM_WriteRegNum_Read: "+EX_MEM_WriteRegNum_Read);

        // MEM_WB
        System.out.println("__________");
        System.out.println("MEM_WB_Write");
        System.out.println("MEM_WB_Control_Write: "+Integer.toHexString(MEM_WB_Control_Write));
        System.out.println("MEM_WB_LWDataValue_Write: "+Integer.toHexString(MEM_WB_LWDataValue_Write));
        System.out.println("MEM_WB_ALUResult_Write: "+Integer.toHexString(MEM_WB_ALUResult_Write));
        System.out.println("MEM_WB_WriteRegNum_Write: "+MEM_WB_WriteRegNum_Write);

        System.out.println("MEM_WB_Read");
        System.out.println("MEM_WB_Control_Read: "+Integer.toHexString(MEM_WB_Control_Read));
        System.out.println("MEM_WB_LWDataValue_Read: "+Integer.toHexString(MEM_WB_LWDataValue_Read));
        System.out.println("MEM_WB_ALUResult_Read: "+Integer.toHexString(MEM_WB_ALUResult_Read));
        System.out.println("MEM_WB_WriteRegNum_Read: "+MEM_WB_WriteRegNum_Read);

    }

    public void Copy_write_to_read()
    {
    // IF_ID
    IF_ID_Read = IF_ID_Write;
    // ID_EX
    ID_EX_ReadReg1Value_Read = ID_EX_ReadReg1Value_Write;
    ID_EX_ReadReg2Value_Read = ID_EX_ReadReg2Value_Write;
    ID_EX_WriteReg1_Read = ID_EX_WriteReg1_Write;
    ID_EX_WriteReg2_Read = ID_EX_WriteReg2_Write;
    ID_EX_Function_Read = ID_EX_Function_Write;
    ID_EX_Opcode_Read = ID_EX_Opcode_Write;
    ID_EX_Control_Read = ID_EX_Control_Write;
    ID_EX_SEOffSet_Read = ID_EX_SEOffSet_Write;
    // EX_MEM
    EX_MEM_Control_Read = EX_MEM_Control_Write;
    EX_MEM_ALUResult_Read =  EX_MEM_ALUResult_Write;
    EX_MEM_WriteRegNum_Read = EX_MEM_WriteRegNum_Write;
    EX_MEM_SWValue_Read = EX_MEM_SWValue_Write;
    EX_MEM_CalcBTA_Read = EX_MEM_CalcBTA_Write;
    EX_MEM_Zero_Write = EX_MEM_Zero_Read;
    // MEM_WB
    MEM_WB_ALUResult_Read = MEM_WB_ALUResult_Write;
    MEM_WB_Control_Read = MEM_WB_Control_Write;
    MEM_WB_LWDataValue_Read = MEM_WB_LWDataValue_Write;
    MEM_WB_WriteRegNum_Read = MEM_WB_WriteRegNum_Write;

    }

}
