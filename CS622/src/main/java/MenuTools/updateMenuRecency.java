package MenuTools;

import Menus.Menu;

import java.util.ArrayList;

public class updateMenuRecency {
    public void updateRecency(ArrayList<Menu> menuArrayList) {
        dateIO.importLastDate();
        int days = dateIO.getDaysSinceLastDate();
        menuArrayList.stream().forEach(menu -> menu.setRecency(menu.getRecency()+days));
    }
}
