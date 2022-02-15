package MenuTools;

import Menus.CookedMenu;
import Menus.Menu;
import Menus.MenuAttributeContainer;
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
            MenuAttributeContainer<String> NameContainer = new MenuAttributeContainer<String>(inputStringList.get(i).get(0).trim());
            MenuAttributeContainer<String> MenuTypeContainer = new MenuAttributeContainer<String>(inputStringList.get(i).get(1).trim());
            MenuAttributeContainer<Double> MenuCostContainer = new MenuAttributeContainer<Double>(Double.parseDouble(inputStringList.get(i).get(2).trim()));
            MenuAttributeContainer<Integer> MenuFrequencyContainer = new MenuAttributeContainer<Integer>(Integer.parseInt(inputStringList.get(i).get(3).trim()));
            MenuAttributeContainer<Integer> MenuRecencyContainer = new MenuAttributeContainer<Integer>(Integer.parseInt(inputStringList.get(i).get(4).trim()));
            // Throw exceptions for key attributes required for the program to run.
            if(NameContainer.getAttribute().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute MenuName is Missing in File row "+i);
            }
            if(MenuTypeContainer.getAttribute().isBlank()) {
                throw new IncompleteMenuException("Menus.Menu Attribute MenuType is Missing in File row "+i);
            }
            if(MenuCostContainer.getAttribute()==null) {
                throw new IncompleteMenuException("Menus.Menu Attribute Cost is Missing in File row "+i);
            }
            if(MenuFrequencyContainer.getAttribute()==null) {
                throw new IncompleteMenuException("Menus.Menu Attribute Frequency is Missing in File row "+i);
            }
            if(MenuRecencyContainer.getAttribute()==null) {
                throw new IncompleteMenuException("Menus.Menu Attribute Recency is Missing in File row "+i);
            }

//            System.out.println(inputStringList.get(i).get(1));
                if (MenuTypeContainer.getAttribute().equals("Ordered")) {

                    OrderedMenu OM = new OrderedMenu();
//                Chow Mein, Ordered, 16.99, 3, 3, Uber Eats, 14.99, 2.00, ,
                    OM.setName(NameContainer.getAttribute());
                    // Add Try/Catch blocks for cost/freq/recency
                    // is not a double/numeric condition here. + these 4 cannot be null
                    OM.setCost(MenuCostContainer.getAttribute());
                    OM.setFrequency(MenuFrequencyContainer.getAttribute());
                    OM.setRecency(MenuRecencyContainer.getAttribute());

                    OM.setDeliveryService(inputStringList.get(i).get(5));
                    OM.setFoodCost(Double.parseDouble(inputStringList.get(i).get(6)));
                    OM.setDeliveryCost(Double.parseDouble(inputStringList.get(i).get(7)));
                    inputMenuList.add(OM);
                    System.out.println("Ordered Menu " + OM.getName() + " was entered into BLT"
                            + " from File " + menuFile);
                }

                if (MenuTypeContainer.getAttribute().equals("Cooked")) {
                    CookedMenu CM = new CookedMenu();
//                Eggplant Casserole, Cooked, 3.44, 0, 0, , , , 4, 60
                    CM.setName(NameContainer.getAttribute());
                    // Add Try/Catch blocks for cost/freq/recency
                    // is not a double/numeric condition here. + these 4 cannot be null
                    CM.setCost(MenuCostContainer.getAttribute());
                    CM.setFrequency(MenuFrequencyContainer.getAttribute());
                    CM.setRecency(MenuRecencyContainer.getAttribute());

                    CM.setCookingDifficulty(Double.parseDouble(inputStringList.get(i).get(8)));
                    CM.setCookingDifficulty(Double.parseDouble(inputStringList.get(i).get(9)));

                    inputMenuList.add(CM);
                    System.out.println("Cooked Menu " + CM.getName() + " was entered into BLT"
                            + " from File " + menuFile);
                }

        }

    return inputMenuList;

    }


}
