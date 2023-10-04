
package Settings;

import Menus.CookedMenu;
import Menus.Menu;
import Menus.OrderedMenu;

public class menuCostConverter<T extends Menu>{
    private T menuToConvert;

    public menuCostConverter(T menuInput){
        this.menuToConvert = menuInput;
    }

    public T convertMenuCurrency(String currencyCode) {

        if(menuToConvert instanceof CookedMenu) {CookedMenu n = new CookedMenu(menuToConvert.getName(), menuToConvert.getCost(), menuToConvert.getFrequency(), menuToConvert.getRecency(), ((CookedMenu) menuToConvert).getCookingDifficulty(), ((CookedMenu) menuToConvert).getCookingTime());
        }
        else {
            OrderedMenu n = new OrderedMenu(menuToConvert.getName(), menuToConvert.getCost(), menuToConvert.getFrequency(), menuToConvert.getRecency(), ((OrderedMenu) menuToConvert).getDeliveryServiceName());
            n.setFoodCost(((OrderedMenu) menuToConvert).getFoodCost());
            n.setDeliveryCost(((OrderedMenu) menuToConvert).getDeliveryCost());
        }
        // default is USD to other Currency
        switch (currencyCode)
        {
            case "GBP":
                menuToConvert.setCost(menuToConvert.getCost()/1.4);
                break;
            case "EUR":
                menuToConvert.setCost(menuToConvert.getCost()/1.2);
                break;
            case "JPY":
                menuToConvert.setCost(menuToConvert.getCost()*100);
                break;
            default:
               System.out.println("We currently do not support "+currencyCode+". Defaulting back to USD.");
        }

        if (menuToConvert instanceof OrderedMenu)
//
        {
            menuToConvert = (T) menuToConvert;
            switch (currencyCode)
            {
                case "GBP":
                    ((OrderedMenu) menuToConvert).setFoodCost(((OrderedMenu) menuToConvert).getFoodCost()/1.4);
                    ((OrderedMenu) menuToConvert).setDeliveryCost(((OrderedMenu) menuToConvert).getDeliveryCost()/1.4);
                    break;
                case "EUR":
                    ((OrderedMenu) menuToConvert).setFoodCost(((OrderedMenu) menuToConvert).getFoodCost()/1.2);
                    ((OrderedMenu) menuToConvert).setDeliveryCost(((OrderedMenu) menuToConvert).getDeliveryCost()/1.2);
                    break;
                case "JPY":
                    ((OrderedMenu) menuToConvert).setFoodCost(((OrderedMenu) menuToConvert).getFoodCost()*100);
                    ((OrderedMenu) menuToConvert).setDeliveryCost(((OrderedMenu) menuToConvert).getDeliveryCost()*100);
                    break;
                default:
                    System.out.println("We currently do not support "+currencyCode+". Defaulting back to USD.");
            }
        }

        return menuToConvert;
    }

}
