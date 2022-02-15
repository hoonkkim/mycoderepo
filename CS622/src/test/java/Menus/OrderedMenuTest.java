package Menus;

import Menus.CookedMenu;
import Menus.OrderedMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderedMenuTest {
    OrderedMenuTest() {
    }
    @Test
    public void testGetCostWhenDeliveryFree() {
        OrderedMenu menu = new OrderedMenu("Test Menus.Menu", 0.0D, 0, 0, "Test Service");
        menu.setCost(10.0, 0.0);
        double result = menu.getCost();
        Assertions.assertEquals(result, 10.0);
    }

    @Test
    public void testGetCostWhenDeliveryNotFree() {
        OrderedMenu menu = new OrderedMenu("Test Menus.Menu", 0.0, 0, 0, "Test Service");
        menu.setCost(10.0, 5.0);
        double result = menu.getCost();
        Assertions.assertEquals(result, 15.0);
    }

    @Test
    public void testGetCostForDifferentCostComponents() {
        OrderedMenu menuCost = new OrderedMenu("Test Menus.Menu 1", 10.0, 0, 0, "Test Service");
        OrderedMenu menuCostParts = new OrderedMenu();
        menuCostParts.setDeliveryCost(3.0);
        menuCostParts.setFoodCost(7.0);

        Assertions.assertEquals(menuCost.getCost(), menuCostParts.getCost());

    }

    @Test
    public void testCompareToWithOrderedMenu() {
        OrderedMenu menuOne = new OrderedMenu("Test Menus.Menu", 10, 1, 1, "Test Service");
        OrderedMenu menuTwo = new OrderedMenu("Test Menus.Menu", 10, 2, 1, "Test Service");
        OrderedMenu menuThree = new OrderedMenu("Test Menus.Menu", 10, 1, 1, "Test Service");

        Assertions.assertEquals(menuOne.compareTo(menuTwo), -1);
        Assertions.assertEquals(menuThree.compareTo(menuOne), 0);
        Assertions.assertEquals(menuTwo.compareTo(menuThree), 1);
    }
    @Test
    public void testCompareToWithCookedMenu() {
        OrderedMenu menuOne = new OrderedMenu("Test Menus.Menu", 10, 2, 1, "Test Service");
        CookedMenu menuTwo = new CookedMenu("Test Menus.Menu", 10, 3, 1, 10, 10);
        CookedMenu menuThree = new CookedMenu("Test Menus.Menu", 10, 1, 1, 10, 10);
        CookedMenu menuFour = new CookedMenu("Test Menus.Menu", 10, 2, 1, 10, 10);

        Assertions.assertEquals(menuOne.compareTo(menuTwo), -1);
        Assertions.assertEquals(menuOne.compareTo(menuThree), 1);
        Assertions.assertEquals(menuOne.compareTo(menuFour), 0);
    }
}