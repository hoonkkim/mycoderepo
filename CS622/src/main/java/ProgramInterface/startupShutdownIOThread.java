package ProgramInterface;

import MenuTools.dateIO;
import MenuTools.inputOutputMenuObjects;
import MenuTools.menuRecommendationSorter;
import MenuTools.updateMenuRecency;
import Menus.Menu;
import Settings.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;


public class startupShutdownIOThread extends Thread {
    public void run(ArrayList<Menu> menuArrayList) throws InterruptedException, IOException, InvalidCurrencyCodeException {
        //Run I/O as part of this thread
//        inputOutputMenuObjects oio = new inputOutputMenuObjects();
//        oio.importMenuObject(menuArrayList);
        Connection conn = dbInteractions.initializeDB();
        dbInteractions.createTables(conn);
        menuArrayList = dbInteractions.readMenusFromDB(conn);

        updateMenuRecency umr = new updateMenuRecency();
        System.out.println("Updating Dates...");
        if(menuArrayList.size() > 0) {umr.updateRecency(menuArrayList);}


        ArrayList<ArrayList<Menu>> convertedMenus = new ArrayList<ArrayList<Menu>>();
        conversionThreads ct = new conversionThreads();
        convertedMenus = ct.convertToAllCurrencies(menuArrayList);
        System.out.println("Converting Currencies...");

        Scanner userInputScanner = new Scanner(System.in);

        System.out.println("Please Enter Your ID of Choice");
        String IDEntry = userInputScanner.nextLine();

        System.out.println("Please Enter Your Currency of Choice");
        String CurrencyEntry = userInputScanner.nextLine();
        user currentUser = null;
        if(CurrencyEntry.equals("USD")) {
            currentUser = new domesticUser(IDEntry);
        }
        else {
            currentUser = new internationalUser(IDEntry, CurrencyEntry);
        }

        // Take User Input for whether they have an ID
        // If Yes -> Attempt Load. Success -> Use their Currency
        // If Yes -> Attempt Load. Fail -> Force to create User ID & Currency
        // If No -> Force to create User ID & Currency
        // From User Object pass Currency Code to determine which list of costs to use.


        // Run the user interface of BLT in a separate thread
        userNavigationInterfaceThread unit = new userNavigationInterfaceThread();
        unit.run(menuArrayList);

        // Join to stall the IO thread because I want it to run output methods after user interface is done.
        this.join();
        
        // export menu and date since interface thread is dead
//        oio.exportMenuObject(menuArrayList);

        dbInteractions.writeMenusToTables(menuArrayList, conn);
        dateIO.exportCurrentDate();
    }
}
