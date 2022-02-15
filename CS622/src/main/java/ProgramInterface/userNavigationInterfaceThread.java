package ProgramInterface;

import MenuTools.*;
import Menus.Menu;
import ProgramInterface.returnToMainMenuOrExit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class userNavigationInterfaceThread extends Thread {
    public static void run(ArrayList<Menu> menuArrayList) {

        Scanner userInputScanner = new Scanner(System.in);
        boolean userIsNotDone = true;
        while (userIsNotDone) {
            boolean userNeedsToSelectMenu = true;
            String userMenuSelection = null;

            while (userNeedsToSelectMenu) {
                System.out.println("Welcome to BLT! Please select what you would like to do.");
                System.out.println("Enter 1 to get recommendations based on past menus"); // Recommendation Sorter
                System.out.println("Enter 2 to manually enter a Menu");
                System.out.println("Enter 3 to enter Menus from a file");
                System.out.println("Enter 0 to Exit the Program");
                userMenuSelection = userInputScanner.nextLine();

                if (userMenuSelection.trim().equals("1")
                        | userMenuSelection.equals("2")
                        | userMenuSelection.equals("3")
                        | userMenuSelection.equals("0")) {
                    userNeedsToSelectMenu = false;
                } else {
                    System.out.println("Invalid Input! Please try again");
                }
            }

            switch (Integer.parseInt(userMenuSelection)) {
                case 1:
                    System.out.println("Sorting your Menus...");

                    if(menuArrayList.size() == 0) {
                        System.out.println("Oops! Looks like you don't have any menus!");
                        userIsNotDone = returnToMainMenuOrExit.returnOrExit();
                        break;}

                    menuRecommendationSorter Rec = new menuRecommendationSorter();
                    menuPrinter mPrinter = new menuPrinter();
                    ArrayList<Menu> sortedMenuArrayList = Rec.sortMenus(menuArrayList);

                    System.out.println("These were the menus in the order they are Recommended:");

                    mPrinter.printMenus(sortedMenuArrayList);

                    System.out.println("Would you like to export your menus to a text file?");
                    System.out.println("Enter 1 to export, 2 to skip");
                    String exportUserEntry = userInputScanner.nextLine();
                    // Instantiate MenuTools.menuRecommendationExporter()
                    if(exportUserEntry.equals("1")) {
                        menuRecommendationExporter Exp = new menuRecommendationExporter();
                        // Run menuRecommenationsExportFormatting() to return an array of Strings to export to file
                        String[] ExportMenuStrings = Exp.menuToString(sortedMenuArrayList);
                        Exp.exportMenus(ExportMenuStrings);
                    }

                    userIsNotDone = returnToMainMenuOrExit.returnOrExit();
                    break;

                case 2:
                    menuEntryUserInterface mEntry = new menuEntryUserInterface();
                    Menu mMenu = mEntry.menuEntry();

                    for (int i = 0; i < menuArrayList.size(); i++)
                    {
                        if(mMenu.isSame(menuArrayList.get(i))) {
                            System.out.println("Previous Entry of "+menuArrayList.get(i).getName()+" was overwritten as it was a duplicate Menu.");
                            menuArrayList.remove(i);
                        }
                    }
                    menuArrayList.add(mMenu);

                    userIsNotDone = returnToMainMenuOrExit.returnOrExit();
                    break;

                // Manual Entry of file directory will happen in release 4 or 5
                case 3:
                    menuFileImporter mfi = new menuFileImporter();
                    ArrayList<Menu> inputMenuList = new ArrayList<Menu>();

                    try {
                        inputMenuList = mfi.fileToMenu("menuInputFile.txt");
                    } catch (IncompleteMenuException e) {
                        e.printStackTrace();
//                        System.exit(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Menu m : inputMenuList) {
                        for (int i = 0; i < menuArrayList.size(); i++)
                        {
                            if(m.isSame(menuArrayList.get(i))) {
                                System.out.println("Previous Entry of "+menuArrayList.get(i).getName()+" was overwritten as it was a duplicate Menu.");
                                menuArrayList.remove(i);
                            }
                        }
                        menuArrayList.add(m);
                    }

                    userIsNotDone = returnToMainMenuOrExit.returnOrExit();
                    break;

                case 0:
                    userIsNotDone = false;
                    break;
            }
        }


    }

}
