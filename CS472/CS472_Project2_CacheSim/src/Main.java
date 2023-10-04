import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    short[] MainMemory = new short[0x800];
        short memoryaddress = 0;

        // build MM
        while (memoryaddress < 0x800)
        {
            MainMemory[memoryaddress] = (short) (memoryaddress & 0b11111111);
            memoryaddress++;
        }

        CacheSlot[] CacheMemory = new CacheSlot[0x10];
        // build Cache
        for (short i = 0; i < 16; i++)
        {
        CacheSlot Slot = new CacheSlot();
        Slot.InitializeSlot(i);
        CacheMemory[i] = Slot;
        }

        // Read Cache

        int endprogram = 0;
        while(endprogram == 0) {
            System.out.println("Please Enter r for Read, w for Write, d for Display or x to end program and hit enter.");
            String Command = new String();
            Scanner keyboard = new Scanner(System.in);
            Command = keyboard.nextLine();



            String useraddressString;
            String uservalueString;

            short useraddressinput;
            short uservalueinput;
            short SlotToUse;
            switch(Command) {
                case "x":
                    System.out.println("Thanks! Bye!");
                    endprogram = 1;
                    break;

                // Read Cache
                case "r":
                    System.out.println("Enter Memory Address you want to read in hex between 000 and 7FF.");
                    useraddressString = keyboard.nextLine();
                    useraddressinput = (short)
                            (
                                (Integer.parseInt(useraddressString.substring(0,1), 16) << 8)
                                + (Integer.parseInt(useraddressString.substring(1,2), 16) << 4)
                                + (Integer.parseInt(useraddressString.substring(2,3), 16))
                            );

                    SlotToUse = (short) (useraddressinput >>> 4 & 0b1111);
                    CacheMemory[SlotToUse].ReadtoSlot(useraddressinput, MainMemory);
                    break;

                // Write Cache
                case "w":
                    System.out.println("Enter Memory Address you want to write to in hex from 000 to 7FF.");
                    useraddressString = keyboard.nextLine();
                    useraddressinput = (short)
                            (
                                (Integer.parseInt(useraddressString.substring(0,1), 16) << 8)
                                + (Integer.parseInt(useraddressString.substring(1,2), 16) << 4)
                                + (Integer.parseInt(useraddressString.substring(2,3), 16))
                            );

                    SlotToUse = (short) (useraddressinput >>> 4 & 0b1111);
                    System.out.println("Enter value you want to write in hex between 00 and FF");
                    uservalueString = keyboard.nextLine();
                    uservalueinput = (short)
                            (
                                (Integer.parseInt(uservalueString.substring(0,1), 16) << 4)
                                + (Integer.parseInt(uservalueString.substring(1,2), 16))
                            );

                    CacheMemory[SlotToUse].WritetoSlot(useraddressinput,uservalueinput,MainMemory);

                    break;

                // DisplayCache
                case "d":

                    System.out.printf("%-5s %-5s %-5s %-5s %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s  %2s \n"
                            , "Slot", "Valid", "Tag", "Dirty"
                            ,"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f");
                for (short i = 0; i < 16; i++) {
                        CacheMemory[i].DisplaySlot();
                    }
                    break;

                default:
                    System.out.println("That wasn't a valid input. Please try again.");
            }
        }


    }
}