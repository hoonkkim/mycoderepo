package MenuTools;

import Menus.CookedMenu;
import Menus.OrderedMenu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class menuScorerTest {

    @Test
    void menuScorerInBoundsNumbersTest() {
        OrderedMenu testMenuOne = new OrderedMenu("TestOne", 10, 1, 14);
        menuScorer mS = new menuScorer();
        mS.menuScorer(testMenuOne);

        assertEquals(11, testMenuOne.getScore());
    }

    @Test
    void menuScorerOutOfBoundNumbersTest() {
        OrderedMenu testMenuOne = new OrderedMenu("TestOne", 10, 1, 14);
        OrderedMenu testMenuTwo = new OrderedMenu("TestOne", 10, 40, 14);
        OrderedMenu testMenuThree = new OrderedMenu("TestOne", 10, 40, -20);
        OrderedMenu testMenuFour = new OrderedMenu("TestOne", 10, -40, -20);
        menuScorer mS = new menuScorer();

        mS.menuScorer(testMenuOne);
        mS.menuScorer(testMenuTwo);
        mS.menuScorer(testMenuThree);
        mS.menuScorer(testMenuFour);

        assertEquals(11, testMenuOne.getScore());
        assertEquals(20, testMenuTwo.getScore());
        assertEquals(10, testMenuThree.getScore());
        assertEquals(0, testMenuFour.getScore());
    }


}