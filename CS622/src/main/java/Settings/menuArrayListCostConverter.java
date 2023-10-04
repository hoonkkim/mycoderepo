package Settings;

import Menus.CookedMenu;
import Menus.Menu;
import Menus.OrderedMenu;

import java.util.ArrayList;

public class menuArrayListCostConverter extends Thread {
    public ArrayList<Menu> run(ArrayList<Menu> menuArrayList, String currencyCode) {
        ArrayList<Menu> target = new ArrayList<Menu>();

        for (Menu m : menuArrayList) {
            if (m instanceof CookedMenu) {
                CookedMenu n = new CookedMenu(m.getName(), m.getCost(), m.getFrequency(), m.getRecency(), ((CookedMenu) m).getCookingDifficulty(), ((CookedMenu) m).getCookingTime());
                menuCostConverter<Menu> mcc = new menuCostConverter<>(n);
                target.add(mcc.convertMenuCurrency(currencyCode));
            } else {
                OrderedMenu o = new OrderedMenu(m.getName(), m.getCost(), m.getFrequency(), m.getRecency(), ((OrderedMenu) m).getDeliveryServiceName());
                o.setFoodCost(((OrderedMenu) m).getFoodCost());
                o.setDeliveryCost(((OrderedMenu) m).getDeliveryCost());
                menuCostConverter<Menu> mcc = new menuCostConverter<>(o);
                target.add(mcc.convertMenuCurrency(currencyCode));
            }
            {
                continue;
            }
        }
        return target;
    }
}