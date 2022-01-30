package MenuTools;

import Menus.Menu;

import java.util.ArrayList;

public class menuRecommendationSorter {
    // this method prints any array of Menus in order.

    // this method sorts an array of Menus in frequency descending order then prints it.
    public ArrayList<Menu> sortMenus(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++)
        {
            sortedMenuArrayList.add(arraysize, null);
        }

        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu compTarget = menuArrayList.get(i);
            int orderRank = 0;
            // Print Menus.Menu Names for Debugging
            //        System.out.println(compTarget.getName());
            for (int j = 0; j < menuArrayList.size(); j++) {

                int compareResult = compTarget.compareTo(menuArrayList.get(j));
//                System.out.println("Current Item is "+compTarget.getName());
//                System.out.println("Current Comp Item is "+menuArrayList.get(j).getName());
                if (menuArrayList.get(j).equals(compTarget) == false) {
                    switch (compareResult) {
                        case 1:
                            break;
                        case -1:
                            orderRank += 1;
                            break;

                    }
                }
//            System.out.println("Compare Result is:"+orderRank);
            }
//            System.out.println("OrderRank "+orderRank);
            sortedMenuArrayList.set(orderRank, compTarget);

            // println for debug
            //        System.out.println(orderRank+" "+sortedMenuArray[orderRank].getName());
        }
        return sortedMenuArrayList;

    }

}
