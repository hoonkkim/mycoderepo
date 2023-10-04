import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    HashTable myTable = new HashTable();

    System.out.println("Hi, welcome to my hashtable");
    boolean exit = false;

    while(!exit)
        {
            System.out.println("Please type A to add new data, D to delete data, V to view the table and X to exit program, then hit enter.");
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();
            switch (input.toUpperCase()) {
                case "A":
                    System.out.println("Enter the integer key where you want to add new data");
                    String addkey = keyboard.nextLine();
                    int a = Integer.parseInt(addkey);

                    System.out.println("Enter the data you want to associate with key "+addkey);
                    String thirdinput = keyboard.nextLine();
                    myTable.put(a, thirdinput);
                    break;
                case "D":
                    System.out.println("Enter the integer key where you want to delete.");
                    String deletekey = keyboard.nextLine();
                    int d = Integer.parseInt(deletekey);
                    myTable.delete(d);
                    break;
                case "V":
                    myTable.printHash();
                    break;
                case "X":
                    System.out.println("Thanks, bye!");
                    exit = true;
                    break;
                default:
                    break;
            }


        }
//// Following Sequence used to test the data
//        myTable.put(1, "Hi"); // fills position 1
//        myTable.put(2, "cool"); // fills position 2
//        myTable.put(1, "Xoxo"); // overwrites position 1
//        myTable.put(1, "Xoxo"); // does nothing
//        myTable.put(32, "wow"); // collides with position 1, and then 2, so writes in position 3
//        myTable.put(3, "what?"); // collides with position 3, so writes in position 4
//
//        myTable.printHash();
//
//        myTable.delete(2); //  delete 2 at position 2
//        myTable.delete(32); // delete 32 at position 3
//
//        myTable.printHash();
//
//        System.out.println(myTable.get(3)); // print key 3 -> "what?"

    }
}
