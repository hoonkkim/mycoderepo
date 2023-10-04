import MenuTools.*;
import Menus.Menu;
import ProgramInterface.dbInteractions;
import ProgramInterface.startupShutdownIOThread;
import ProgramInterface.userNavigationInterfaceThread;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, IncompleteMenuException, ClassNotFoundException, InterruptedException, SQLException {

        // Figure out project Directory to check if SQLite DB exists.

        // Bring in ArrayList to use to store Menu Objects
        ArrayList<Menu> menuArrayList = new ArrayList<>();
        startupShutdownIOThread iot = new startupShutdownIOThread();
        iot.run(menuArrayList);

        System.out.println("Thanks for using BLT!");
    }


}





//    // Dummy Menus for Testing/Demo Purposes
//    Menu OrangeChicken = new OrderedMenu("Orange Chicken", 13.95, 1
//            , 2, "Uber Eats");
//        menuArrayList.add(OrangeChicken);
//
//                Menu PadThai = new OrderedMenu("Pad Thai", 14.99, 2
//                , 1, "DoorDash");
//                menuArrayList.add(PadThai);
//
//                Menu ClubSandwich = new OrderedMenu("Club Sandwich", 10.50, 7
//                , 0, "Uber Eats");
//                menuArrayList.add(ClubSandwich);
//
//                Menu Sushi = new OrderedMenu("Sushi", 24.00, 3
//                , 0, "Grubhub");
//                menuArrayList.add(Sushi);
//
//                Menu KimchiFriedRice = new CookedMenu("Kimchi Fried Rice", 4.31
//                , 8, 0, 4.2, 30);
//                menuArrayList.add(KimchiFriedRice);
//