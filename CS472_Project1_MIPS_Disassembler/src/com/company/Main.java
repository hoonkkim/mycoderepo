package com.company;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // 0x032BA020, 0x8CE90014, 0x12A90003, 0x022DA822, 0xADB30020, 0x02697824, 0xAE8FFFF4, 0x018C6020, 0x02A4A825, 0x158FFFF7, 0x8ECDFFF0
        // Starting PC: 9A040
        // 0. Throw these values into an Array of MIPS Command Objects with a loop
        // 1. What kind of command is it? I/R.
        // 2. What is the command?
        // 3. Decode command.
        // 4. Calculate Memory Location. And register numbers.
        // 5. Back to 1.

// Build Array and Allocate Commands
        int[] CommandArray = new int[11];
        CommandArray[0] = 0x032BA020;
        CommandArray[1] = 0x8CE90014;
        CommandArray[2] = 0x12A90003;
        CommandArray[3] = 0x022DA822;
        CommandArray[4] = 0xADB30020;
        CommandArray[5] = 0x02697824;
        CommandArray[6] = 0xAE8FFFF4;
        CommandArray[7] = 0x018C6020;
        CommandArray[8] = 0x02A4A825;
        CommandArray[9] = 0x158FFFF7;
        CommandArray[10] = 0x8ECDFFF0;

        // We need to keep track of this
        int Location = 0x9A040;
        int loopcounter = 0;


// Loop here
        while (loopcounter < 11) {
            int Instruction = CommandArray[loopcounter];

            // Declare Register Ints and Function Name with every iteration so there's no carryover by mistake
            String FunctionName = new String();
            int rs = -999999999;
            int rt = -999999999;
            int rd = -999999999;
            int function = -999999999;
            Integer immInt = 0;
            short immShort = 0;

            // Print the actual instruction in Binary to debug and build
//            System.out.println(Integer.toBinaryString(Instruction));

            // First identify if its an R or I type function
            int opcode = Instruction >>> 26 & 0b111111;
            // System.out.println(Integer.toHexString(opcode));

            if (opcode == 0) { // R-type functions
                // break down the instruction and assign register values & function value
                function = Instruction & 0b111111;
                rs = Instruction >>> 21 & 0b11111;
                rt = Instruction >>> 16 & 0b11111;
                rd = Instruction >>> 11 & 0b11111;

                // switch statement to assign function names
                switch (function) {
                    case 0x20: // add
                        FunctionName = "add";
                        break;
                    case 0x22: // sub
                        FunctionName = "sub";
                        break;
                    case 0x24: // and
                        FunctionName = "and";
                        break;
                    case 0x25: // or
                        FunctionName = "or";
                        break;
                    default:
                        System.out.println("Seems like a R");
                        System.out.println(function);
                }
                System.out.println(Integer.toHexString(Location)+" "+FunctionName + " $" + rd + " $" + rs + " $" + rt);

            } else {
                // break down the instruction and assign register values & imm value int/short
                rs = Instruction >>> 21 & 0b11111;
                rt = Instruction >>> 16 & 0b11111;
                immInt = ((Instruction & 0xFFFF));
                immShort = immInt.shortValue();

                switch (opcode) {
                    case 0x23: // lw
                        FunctionName = "lw";
//                        System.out.println(Integer.toBinaryString(immShort));
                        System.out.println(Integer.toHexString(Location)+" "+FunctionName + " $" + rt + ", "+immShort+"($" + rs+")");
                        break;
                    case 0x2b: // sw
                        FunctionName = "sw";
//                        System.out.println(Integer.toBinaryString(immShort));
                        System.out.println(Integer.toHexString(Location)+" "+FunctionName + " $" + rt + ", "+immShort+"($" + rs+")");
                        break;
                    case 0x04: // beq
                        FunctionName = "beq";
                        System.out.println(Integer.toHexString(Location)+" "+FunctionName + " $" + rs + ", "+"$" + rt+" address "+Integer.toHexString(Location+(immShort << 2)+4));
//                        System.out.println(immShort << 2);
//                        Location = Location + (immShort << 2);
                        break;
                    case 0x05: // bne
                        FunctionName = "bne";
                        System.out.println(Integer.toHexString(Location)+" "+FunctionName + " $" + rs + ", "+"$" + rt+" address "+Integer.toHexString(Location+(immShort << 2)+4));
//                        System.out.println(immShort << 2);
//                        Location = Location + (immShort << 2);
                        break;
                    default:
                        System.out.println("Seems like an I");
                        System.out.println(Integer.toHexString(opcode));

                }
            }

            Location = Location + 4;
            loopcounter = loopcounter + 1;


        }


    }
}