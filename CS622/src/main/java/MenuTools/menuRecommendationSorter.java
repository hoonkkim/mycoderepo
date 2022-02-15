package MenuTools;

import Menus.Menu;

import java.util.ArrayList;

public class menuRecommendationSorter {

    // this method sorts an arraylist of Menus in score descending order.
    public ArrayList<Menu> sortMenus(ArrayList<Menu> menuArrayList) {
        // need to implement tiebreakers in the future
        ArrayList<Menu> sortedMenuArrayList = new ArrayList<Menu>();

        // Score all menus.
        menuScorer mS = new menuScorer();
        for (int arraysize = 0; arraysize < menuArrayList.size(); arraysize++)
        {
            mS.menuScorer(menuArrayList.get(arraysize));
        }

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
                        case 0:
                            if(menuArrayList.get(j).getName().compareToIgnoreCase(compTarget.getName()) <= 0) {
                                orderRank += 1;
                                break;
                            }
                            else {
                                break;
                            }
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
