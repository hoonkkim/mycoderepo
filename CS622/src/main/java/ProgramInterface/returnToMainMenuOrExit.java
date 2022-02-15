package ProgramInterface;

import java.util.Scanner;
// This is brief selection process for the user to either exit the program or return to the beginning
public class returnToMainMenuOrExit {
    public static boolean returnOrExit() {
        boolean secondCaseUserNeedstoSelectMenu = true;
        boolean backToMenu = false;
        Scanner userInputScanner = new Scanner(System.in);
        String secondCaseUserInput;
        while (secondCaseUserNeedstoSelectMenu) {
            System.out.println("Enter 1 to Return to the Main Menu");
            System.out.println("Enter 2 to End Program");

            secondCaseUserInput = userInputScanner.nextLine();
            switch(secondCaseUserInput.trim()) {
                case "1":
                    System.out.println("You picked 1, return to menu.");
                    secondCaseUserNeedstoSelectMenu = false;
                    backToMenu = true;
                    break;
                case "2":
                    System.out.println("You picked 2, exit program.");
                    secondCaseUserNeedstoSelectMenu = false;
                    backToMenu = false;
                    break;
                default:
                    System.out.println("Invalid Input. Try Again.");
                    break;
            }
        }
    return backToMenu;
    }
}
