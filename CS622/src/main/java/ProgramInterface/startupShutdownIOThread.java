package ProgramInterface;

import MenuTools.dateIO;
import MenuTools.inputOutputMenuObjects;
import MenuTools.updateMenuRecency;
import Menus.Menu;
import java.io.IOException;
import java.util.ArrayList;

public class startupShutdownIOThread extends Thread {
    public void run(ArrayList<Menu> menuArrayList) throws InterruptedException, IOException {
        //Run I/O as part of this thread
        inputOutputMenuObjects oio = new inputOutputMenuObjects();
        oio.importMenuObject(menuArrayList);
        updateMenuRecency umr = new updateMenuRecency();
        System.out.println("Updating Dates...");
        if(menuArrayList.size() > 0) {umr.updateRecency(menuArrayList);}
        // Run the user interface of BLT in a separate thread
        userNavigationInterfaceThread unit = new userNavigationInterfaceThread();
        unit.run(menuArrayList);

        // Join to stall the IO thread because I want it to run output methods after user interface is done.
        this.join();
        
        // export menu and date since interface thread is dead
        oio.exportMenuObject(menuArrayList);
        dateIO.exportCurrentDate();

    }
}
