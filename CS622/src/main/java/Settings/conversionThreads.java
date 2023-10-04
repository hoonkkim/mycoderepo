package Settings;

import Menus.Menu;

import java.util.ArrayList;

public class conversionThreads {

    public ArrayList<ArrayList<Menu>> convertToAllCurrencies (ArrayList<Menu> menuArrayList) {
        // GPB Thread
        menuArrayListCostConverter gbpThread = new menuArrayListCostConverter();
        ArrayList<Menu> g = gbpThread.run(menuArrayList, "GBP");
        // EUR Thread
        menuArrayListCostConverter eurThread = new menuArrayListCostConverter();
        ArrayList<Menu> e = eurThread.run(menuArrayList, "EUR");
        // JPY Thread
        menuArrayListCostConverter jpyThread = new menuArrayListCostConverter();
        ArrayList<Menu> k = jpyThread.run(menuArrayList, "JPY");

        ArrayList<ArrayList<Menu>> ALCollection = new ArrayList<ArrayList<Menu>>();
        ALCollection.add(g);
        ALCollection.add(e);
        ALCollection.add(k);

        return ALCollection;
    }
}
