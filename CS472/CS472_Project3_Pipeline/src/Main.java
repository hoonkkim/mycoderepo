import java.beans.PropertyChangeListenerProxy;

public class Main {

    public static void main(String[] args) {
        // Initialize Main Memory
        int[] Main_Mem = new int[0x400];
        for(short i = 0; i<0x400; i++)
        {
            Main_Mem[i] = (short) (i & 0b11111111);
        }

        // initialize Registers
        int[] Regs = new int[32];
        Regs[0] = 0;
        for (int i = 1; i<32; i++)
        {
            Regs[i] = 0x100+i;
        }

        // initialize Instructions
        int[] InstructionCache = new int[12];
        InstructionCache[0] = 0xa1020000; // SB $0 0 $8
        InstructionCache[1] = 0x810AFFFC; // LB $10 0xFFFC $8
        InstructionCache[2] = 0x00831820; // ADD $3 $4 $3
        InstructionCache[3] = 0x01263820; // ADD $7 $9 $6
        InstructionCache[4] = 0x01224820; // ADD $9 $9 $2
        InstructionCache[5] = 0x81180000; // LB $24 0x0000 $8
        InstructionCache[6] = 0x81510010; // LB $17 0x0010 $10
        InstructionCache[7] = 0x00624022; // SUB $8 $3 $2
        InstructionCache[8] = 0x00000000; // NoOp
        InstructionCache[9] = 0x00000000; // NoOp
        InstructionCache[10] = 0x00000000; // NoOp
        InstructionCache[11] = 0x00000000; // NoOp


        // Initialize all other things
        PipeLineControl Pipeline = new PipeLineControl();
        int InstructionIndex = 0;

        while(InstructionIndex < InstructionCache.length)
        {
            System.out.println("Clock Cycle "+(InstructionIndex+1));
            Pipeline.IF_STAGE(InstructionCache, InstructionIndex);
            Pipeline.ID_STAGE(Regs);
            Pipeline.EX_STAGE(Regs, Main_Mem);
            Pipeline.MEM_STAGE(Main_Mem);
            Pipeline.WB_Stage(Regs);
            System.out.println("_____");
            Pipeline.Print_Out_Everything(Regs, Regs.length);
            System.out.println("_____");
            Pipeline.Copy_write_to_read();
            InstructionIndex++;
        }
        // Actually Run the Program


    }


}
