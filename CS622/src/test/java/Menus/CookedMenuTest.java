package Menus;

import Menus.CookedMenu;
import Menus.OrderedMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CookedMenuTest {
    CookedMenuTest() {}

    @Test
    public void testCompareToWithOrderedMenu() {
        CookedMenu menuZero = new CookedMenu("Test Menus.Menu", 10, 2, 1, 10, 10);
        OrderedMenu menuOne = new OrderedMenu("Test Menus.Menu", 10, 1, 1, "Test Service");
        OrderedMenu menuTwo = new OrderedMenu("Test Menus.Menu", 10, 2, 1, "Test Service");
        OrderedMenu menuThree = new OrderedMenu("Test Menus.Menu", 10, 3, 1, "Test Service");

        Assertions.assertEquals(menuZero.compareTo(menuOne), 1);
        Assertions.assertEquals(menuZero.compareTo(menuTwo), 0);
        Assertions.assertEquals(menuZero.compareTo(menuThree), -1);
    }
    @Test
    public void testCompareToWithCookedMenu() {
        CookedMenu menuOne = new CookedMenu("Test Menus.Menu", 10, 2, 1, 10, 10);
        CookedMenu menuTwo = new CookedMenu("Test Menus.Menu", 10, 1, 1, 10, 10);
        CookedMenu menuThree = new CookedMenu("Test Menus.Menu", 10, 3, 1, 10, 10);
        CookedMenu menuFour = new CookedMenu("Test Menus.Menu", 10, 2, 1, 10, 10);

        Assertions.assertEquals(menuOne.compareTo(menuTwo), 1);
        Assertions.assertEquals(menuOne.compareTo(menuThree), -1);
        Assertions.assertEquals(menuOne.compareTo(menuFour), 0);
    }
}