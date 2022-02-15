package MenuTools;

import Menus.Menu;
import Menus.OrderedMenu;

import java.util.ArrayList;
/*
This Class & Method sort any ArrayLists of Menus in
*/
public class menuPrinter {
    public void printMenus(ArrayList<Menu> menuArrayList) {
//        System.out.println("Recommendations by Entry Order.");

        for (int i = 0; i < menuArrayList.size(); i++) {
            String MenuName = menuArrayList.get(i).getName();
            if (menuArrayList.get(i) instanceof OrderedMenu) {
                // Current output method is crude and hardcoded. This will get cleaned up in later releases
                // Iterates through the sorted array of Menus.Menu Objects to print recommendations
                // We downcast from Menus.Menu to Menus.OrderedMenu in this case to get the delivery service name.
                System.out.println("Recommendation #"+(1+i)+". "+MenuName+", Frequency: "+menuArrayList.get(i).getFrequency()+
                        ", Delivered by: "+((OrderedMenu) menuArrayList.get(i)).getDeliveryServiceName()
                        +", Cost: $"+menuArrayList.get(i).getCost()
                        +", Score: "+menuArrayList.get(i).getScore());
            }
            else {
                System.out.println("Recommendation #"+(1+i)+". "+MenuName+", Frequency: "+menuArrayList.get(i).getFrequency()
                        +", Cooked by: Me"+", Cost: $"+menuArrayList.get(i).getCost()+", Score: "+menuArrayList.get(i).getScore());
            }
        }
    }
}
