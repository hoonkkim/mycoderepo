import MenuTools.menuRecommendationSorter;
import Menus.CookedMenu;
import Menus.Menu;
import Menus.OrderedMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class menuRecommendationSorterTest {

    menuRecommendationSorterTest() {};

    @Test
    void testSortMenusWithRandomFrequencyOrder() {
        OrderedMenu menuOne = new OrderedMenu("Test Menus.Menu One", 0.0D, 0, 0, "Test Service");
        CookedMenu menuTwo = new CookedMenu("Test Menus.Menu Two", 0.0D, 99, 0, 5, 10);
        OrderedMenu menuThree = new OrderedMenu("Test Menus.Menu Three", 0.0D, 3, 0, "Test Service");
        CookedMenu menuFour = new CookedMenu("Test Menus.Menu Four", 0.0D, 47, 0, 10, 5);

        ArrayList<Menu> menuArrayList = new ArrayList<>();
        menuArrayList.add(menuOne);
        menuArrayList.add(menuTwo);
        menuArrayList.add(menuThree);
        menuArrayList.add(menuFour);

        menuRecommendationSorter sorter = new menuRecommendationSorter();
        ArrayList<Menu> sortedArrayList = sorter.sortMenus(menuArrayList);

        Assertions.assertEquals(sortedArrayList.get(0).getName(), menuTwo.getName());
        Assertions.assertEquals(sortedArrayList.get(1).getName(), menuFour.getName());
        Assertions.assertEquals(sortedArrayList.get(2).getName(), menuThree.getName());
        Assertions.assertEquals(sortedArrayList.get(3).getName(), menuOne.getName());
    }
}