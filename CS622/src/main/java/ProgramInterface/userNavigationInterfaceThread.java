package ProgramInterface;

import MenuTools.*;
import Menus.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class userNavigationInterfaceThread extends Thread {
    public void run(ArrayList<Menu> menuArrayList) {
        // Scanner for User Input to Navigate
        Scanner userInputScanner = new Scanner(System.in);

        boolean userIsNotDone = true;
        while (userIsNotDone) {
            // Run thread for sorting menus so we already have it ready.
            menuRecommendationSorter mRS = new menuRecommendationSorter();
            mRS.run(menuArrayList);
            ArrayList<ArrayList<Menu>> menuArrayListArrayList = mRS.returnArrayLists();

            boolean userNeedsToSelectMenu = true;
            String userMenuSelection = null;

            while (userNeedsToSelectMenu) {
                System.out.println("Welcome to BLT! Please select what you would like to do.");
                System.out.println("Enter 1 to get recommendations based on past menus"); // Recommendation Sorter
                System.out.println("Enter 2 to manually enter a Menu");
                System.out.println("Enter 3 to enter Menus from a file");
                System.out.println("Enter 0 to Exit the Program");
                userMenuSelection = userInputScanner.nextLine().trim();

                if (userMenuSelection.equals("1")
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

                    if(menuArrayList.size() == 0) {
                        System.out.println("Oops! Looks like you don't have any menus!");
                        userIsNotDone = returnToMainMenuOrExit.returnOrExit();
                        break;}

                    ArrayList<Menu> menuArrayListForExport = new ArrayList<Menu>();
                    String userTypeSelection = new String();
                    boolean userNeedsToSelectMenuType = true;
                    boolean userNeedsToSelectSort = true;
                    String userSortSelection = new String();
                    while(userNeedsToSelectMenuType) {
                        System.out.println("Please indicate which subset of menus you would like BLT to recommend"); // Recommendation Sorter
                        System.out.println("Enter 1 for All Menus");
                        System.out.println("Enter 2 for only Ordered Menus");
                        System.out.println("Enter 3 for only Cooked Menus");
                        userTypeSelection = userInputScanner.nextLine();
                        switch (userTypeSelection) {
                            case "1":
                                System.out.println("You selected All Menus");
                                // generate a new arraylist to export
                                // Index Guide:
                                // 0 Score Desc, 1-2 Cost Asc-Desc, 3-4 Frequency Asc-Desc
                                // 5-6 Recency Asc-Desc, 7-8 Name Asc-Desc
                                while (userNeedsToSelectSort) {
                                    System.out.println("Please the Method for Sorting Your Menus"); // Recommendation Sorter
                                    System.out.println("Enter 1 to Sort by Score");
                                    System.out.println("Enter 2 to Sort by Cost Ascending");
                                    System.out.println("Enter 3 to Sort by Cost Descending");
                                    System.out.println("Enter 4 to Sort by Frequency Ascending");
                                    System.out.println("Enter 5 to Sort by Frequency Descending");
                                    System.out.println("Enter 6 to Sort by Recency Ascending");
                                    System.out.println("Enter 7 to Sort by Recency Descending");
                                    System.out.println("Enter 8 to Sort by Name Ascending");
                                    System.out.println("Enter 9 to Sort by Name Descending");
                                    userSortSelection = userInputScanner.nextLine();

                                    switch (userSortSelection) {
                                        case "1":
                                            menuArrayListForExport = menuArrayListArrayList.get(0);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "2":
                                            menuArrayListForExport = menuArrayListArrayList.get(1);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "3":
                                            menuArrayListForExport = menuArrayListArrayList.get(2);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "4":
                                            menuArrayListForExport = menuArrayListArrayList.get(3);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "5":
                                            menuArrayListForExport = menuArrayListArrayList.get(4);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "6":
                                            menuArrayListForExport = menuArrayListArrayList.get(5);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "7":
                                            menuArrayListForExport = menuArrayListArrayList.get(6);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "8":
                                            menuArrayListForExport = menuArrayListArrayList.get(7);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "9":
                                            menuArrayListForExport = menuArrayListArrayList.get(8);
                                            userNeedsToSelectSort = false;
                                            break;
                                        default:
                                            System.out.println("Invalid Input! Please try again");
                                    }
                                }
                                userNeedsToSelectMenuType = false;
                                break;

                            case "2":
                                System.out.println("You selected Ordered Menus Only");
                                // generate a new arraylist to export
                                // Index Guide:
                                // 0 Score Desc, 1-2 Cost Asc-Desc, 3-4 Frequency Asc-Desc
                                // 5-6 Recency Asc-Desc, 7-8 Name Asc-Desc
                                while (userNeedsToSelectSort) {
                                    System.out.println("Please the Method for Sorting Your Menus"); // Recommendation Sorter
                                    System.out.println("Enter 1 to Sort by Score");
                                    System.out.println("Enter 2 to Sort by Cost Ascending");
                                    System.out.println("Enter 3 to Sort by Cost Descending");
                                    System.out.println("Enter 4 to Sort by Frequency Ascending");
                                    System.out.println("Enter 5 to Sort by Frequency Descending");
                                    System.out.println("Enter 6 to Sort by Recency Ascending");
                                    System.out.println("Enter 7 to Sort by Recency Descending");
                                    System.out.println("Enter 8 to Sort by Name Ascending");
                                    System.out.println("Enter 9 to Sort by Name Descending");
                                    userSortSelection = userInputScanner.nextLine();

                                    switch (userSortSelection) {
                                        case "1":
                                            menuArrayListForExport = menuArrayListArrayList.get(9);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "2":
                                            menuArrayListForExport = menuArrayListArrayList.get(10);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "3":
                                            menuArrayListForExport = menuArrayListArrayList.get(11);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "4":
                                            menuArrayListForExport = menuArrayListArrayList.get(12);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "5":
                                            menuArrayListForExport = menuArrayListArrayList.get(13);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "6":
                                            menuArrayListForExport = menuArrayListArrayList.get(14);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "7":
                                            menuArrayListForExport = menuArrayListArrayList.get(15);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "8":
                                            menuArrayListForExport = menuArrayListArrayList.get(16);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "9":
                                            menuArrayListForExport = menuArrayListArrayList.get(17);
                                            userNeedsToSelectSort = false;
                                            break;
                                        default:
                                            System.out.println("Invalid Input! Please try again");
                                    }
                                }
                            userNeedsToSelectMenuType = false;
                            break;

                            case "3":
                                System.out.println("You selected Cooked Menus Only");
                                // generate a new arraylist to export
                                // Index Guide:
                                // 0 Score Desc, 1-2 Cost Asc-Desc, 3-4 Frequency Asc-Desc
                                // 5-6 Recency Asc-Desc, 7-8 Name Asc-Desc
                                while (userNeedsToSelectSort) {
                                    System.out.println("Please the Method for Sorting Your Menus"); // Recommendation Sorter
                                    System.out.println("Enter 1 to Sort by Score");
                                    System.out.println("Enter 2 to Sort by Cost Ascending");
                                    System.out.println("Enter 3 to Sort by Cost Descending");
                                    System.out.println("Enter 4 to Sort by Frequency Ascending");
                                    System.out.println("Enter 5 to Sort by Frequency Descending");
                                    System.out.println("Enter 6 to Sort by Recency Ascending");
                                    System.out.println("Enter 7 to Sort by Recency Descending");
                                    System.out.println("Enter 8 to Sort by Name Ascending");
                                    System.out.println("Enter 9 to Sort by Name Descending");
                                    userSortSelection = userInputScanner.nextLine();

                                    switch (userSortSelection) {
                                        case "1":
                                            menuArrayListForExport = menuArrayListArrayList.get(18);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "2":
                                            menuArrayListForExport = menuArrayListArrayList.get(19);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "3":
                                            menuArrayListForExport = menuArrayListArrayList.get(20);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "4":
                                            menuArrayListForExport = menuArrayListArrayList.get(21);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "5":
                                            menuArrayListForExport = menuArrayListArrayList.get(22);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "6":
                                            menuArrayListForExport = menuArrayListArrayList.get(23);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "7":
                                            menuArrayListForExport = menuArrayListArrayList.get(24);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "8":
                                            menuArrayListForExport = menuArrayListArrayList.get(25);
                                            userNeedsToSelectSort = false;
                                            break;
                                        case "9":
                                            menuArrayListForExport = menuArrayListArrayList.get(26);
                                            userNeedsToSelectSort = false;
                                            break;
                                        default:
                                            System.out.println("Invalid Input! Please try again");
                                    }
                                }
                                userNeedsToSelectMenuType = false;
                                break;
                            default:
                                System.out.println("Invalid Menu Type input! Please try again!");
                        }

                    }

                    // Prepare to display the sorted list of menus
                    menuPrinter mPrinter = new menuPrinter();


                    System.out.println("These were the menus in the order they are sorted:");
                    mPrinter.printMenus(menuArrayListForExport);

                    System.out.println("Would you like to export your menus to a text file?");
                    System.out.println("Enter 1 to export, 2 to skip");
                    String exportUserEntry = userInputScanner.nextLine();
                    // Instantiate MenuTools.menuRecommendationExporter()
                    if(exportUserEntry.equals("1")) {
                        menuRecommendationExporter Exp = new menuRecommendationExporter();
                        // Run menuRecommenationsExportFormatting() to return an array of Strings to export to file
                        String[] ExportMenuStrings = Exp.menuToString(menuArrayListForExport);
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
                    userInputScanner.close();
                    break;
            }
        }


    }

}
