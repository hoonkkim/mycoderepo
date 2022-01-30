package MenuTools;

import Menus.CookedMenu;
import Menus.Menu;
import Menus.OrderedMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class menuFileImporter  {
    // Needs to do two things.
    // 1. Import a file line by line and create new menu objects from it
    // 2. Check database to see if identical menu item exists. And if it does
    // , update attributes
    // 3. Have a custom exception for rows that do have empty rows.
    public static ArrayList<Menu> fileToMenu(String menuFile) throws IOException, IncompleteMenuException {
        // read file
        FileReader fr = new FileReader(menuFile);
        BufferedReader br = new BufferedReader(fr);

        // Fill an arraylist of Strings with each row in the file
        List<List<String>> inputStringList = new ArrayList<>();
        // Discard Line 1, which should be the header
        br.readLine();
        // while loop to loop through the file for new menu items
        while(br.ready())
        {
            String rowInputString = br.readLine();
            inputStringList.add(List.of(rowInputString.split(",")));
        }
        // Close the file after read.
        br.close();

        // With conditions, create menu items with each item in the arraylist and add it to the list of inputs
        ArrayList<Menu> inputMenuList = new ArrayList<>();
        for(int i = 0; i < inputStringList.size(); i++)
        {
            // Throw exceptions for key attributes required for the program to run.
            if(inputStringList.get(i).get(0).trim().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute MenuName is Missing in File row "+i);
            }
            if(inputStringList.get(i).get(1).trim().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute MenuType is Missing in File row "+i);
            }
            if(inputStringList.get(i).get(2).trim().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute Cost is Missing in File row "+i);
            }
            if(inputStringList.get(i).get(3).trim().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute Frequency is Missing in File row "+i);
            }
            if(inputStringList.get(i).get(4).trim().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute Recency is Missing in File row "+i);
            }
            else {
//            System.out.println(inputStringList.get(i).get(1));
                if (inputStringList.get(i).get(1).equals("Ordered")) {

                    OrderedMenu OM = new OrderedMenu();
//                Chow Mein, Ordered, 16.99, 3, 3, Uber Eats, 14.99, 2.00, ,
                    OM.setName(inputStringList.get(i).get(0));
                    // Add Try/Catch blocks for cost/freq/recency
                    // is not a double/numeric condition here. + these 4 cannot be null
                    OM.setCost(Double.parseDouble(inputStringList.get(i).get(2)));
                    OM.setFrequency(Integer.parseInt(inputStringList.get(i).get(3)));
                    OM.setRecency(Integer.parseInt(inputStringList.get(i).get(4)));
                    OM.setDeliveryService(inputStringList.get(i).get(5));
                    OM.setFoodCost(Double.parseDouble(inputStringList.get(i).get(6)));
                    OM.setDeliveryCost(Double.parseDouble(inputStringList.get(i).get(7)));
                    inputMenuList.add(OM);
                    System.out.println("Ordered Menus.Menu " + OM.getName() + " was entered into BLT"
                            + " from File " + menuFile);
                }

                if (inputStringList.get(i).get(1).equals("Cooked")) {
                    CookedMenu CM = new CookedMenu();
//                Eggplant Casserole, Cooked, 3.44, 0, 0, , , , 4, 60
                    CM.setName(inputStringList.get(i).get(0));
                    // Add Try/Catch blocks for cost/freq/recency
                    // is not a double/numeric condition here. + these 4 cannot be null
                    CM.setCost(Double.parseDouble(inputStringList.get(i).get(2)));
                    CM.setFrequency(Integer.parseInt(inputStringList.get(i).get(3)));
                    CM.setRecency(Integer.parseInt(inputStringList.get(i).get(4)));
                    CM.setCookingDifficulty(Double.parseDouble(inputStringList.get(i).get(8)));
                    CM.setCookingDifficulty(Double.parseDouble(inputStringList.get(i).get(9)));

                    inputMenuList.add(CM);
                    System.out.println("Cooked Menus.Menu " + CM.getName() + " was entered into BLT"
                            + " from File " + menuFile);
                }
            }
        }

    return inputMenuList;

    }


}
