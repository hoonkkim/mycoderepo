package ProgramInterface;

import Menus.CookedMenu;
import Menus.Menu;
import Menus.OrderedMenu;
import org.junit.jupiter.api.Order;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;


public class dbInteractions {

    public static Connection initializeDB() {

        String SysDir = System.getProperty("user.dir");

        // Boolean check for menuDB.db in project directory
        File tempFile = new File(SysDir + "/menuDB.db");
        boolean dbExists = tempFile.exists();
        String urlString = "jdbc:sqlite:" + SysDir + "/menuDB.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(urlString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTables(Connection con) {
        DatabaseMetaData dbm = null;
        try {
            // menuTypes Table: MenuID + MenuTypeID
            String sql = "CREATE TABLE IF NOT EXISTS menuTypes (\n"
                    + "	menuID integer NOT NULL,\n"
                    + " name string PRIMARY KEY, \n"
                    + "	menuTypeID integer NOT NULL\n"
                    + ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
//            System.out.println("menuTypes Table was created");

            // OrderedMenu Table: MenuID (PK) + all the attributes
            sql = "CREATE TABLE IF NOT EXISTS orderedMenus (\n"
                    + "	menuID integer NOT NULL,\n"
                    + " name String NOT NULL,\n"
                    + " cost double NOT NULL,\n"
                    + " frequency int NOT NULL,\n"
                    + " recency int NOT NULL,\n"
                    + " foodCost double, \n"
                    + " deliveryCost double, \n"
                    + " deliveryServiceName String, \n"
                    + " FOREIGN KEY (menuID, name) REFERENCES menuTypes(menuID , name) \n"
                    + ");";
            stmt.execute(sql);
//             System.out.println("ordererdMenus Table was created");

            // Parse for CookedMenu Table: MenuID (PK) - all the attributes
            sql = "CREATE TABLE IF NOT EXISTS cookedMenus (\n"
                    + "	menuID integer NOT NULL,\n"
                    + " name String NOT NULL,\n"
                    + " cost double NOT NULL,\n"
                    + " frequency int NOT NULL,\n"
                    + " recency int NOT NULL,\n"
                    + " cookingtime double, \n"
                    + " cookingdifficulty double, \n"
                    + " FOREIGN KEY (menuID, name) REFERENCES menuTypes(menuID, name) \n"
                    + ");";
            stmt.execute(sql);
//            System.out.println("cookedMenus Table was created");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void writeMenusToTables(ArrayList<Menu> menuArrayList, Connection conn) {

        // Truncate all tables
        String sql = "DELETE FROM menuTypes";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "DELETE FROM cookedMenus";
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "DELETE FROM orderedMenus";
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        for (int i = 0; i < menuArrayList.size(); i++) {
            int TypeID;
            int menuID = i;
            Menu menu = menuArrayList.get(i);

            if (menu.getType().equals("Cooked")) {
                TypeID = 1;
                CookedMenu cm = (CookedMenu) menu;
                sql = "INSERT INTO cookedMenus(menuID,name,cost,frequency,recency,cookingtime,cookingdifficulty) VALUES(?,?,?,?,?,?,?)";
                try (
                        PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, menuID);
                    pstmt.setString(2, cm.getName());
                    pstmt.setDouble(3, cm.getCost());
                    pstmt.setInt(4, cm.getFrequency());
                    pstmt.setInt(5, cm.getRecency());
                    pstmt.setDouble(6, cm.getCookingTime());
                    pstmt.setDouble(7, cm.getCookingDifficulty());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                TypeID = 2;
                OrderedMenu om = (OrderedMenu) menu;
                sql = "INSERT INTO orderedMenus(menuID,name,cost,frequency,recency,foodcost,deliverycost, deliveryservicename) VALUES(?,?,?,?,?,?,?,?)";
                try (
                        PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, menuID);
                    pstmt.setString(2, om.getName());
                    pstmt.setDouble(3, om.getCost());
                    pstmt.setInt(4, om.getFrequency());
                    pstmt.setInt(5, om.getRecency());
                    pstmt.setDouble(6, om.getFoodCost());
                    pstmt.setDouble(7, om.getDeliveryCost());
                    pstmt.setString(8, om.getDeliveryServiceName());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }

            sql = "INSERT INTO menuTypes(menuID,menuTypeID,name) VALUES(?,?,?)";
            try (
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, menuID);
                pstmt.setInt(2, TypeID);
                pstmt.setString(3, menuArrayList.get(i).getName());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ArrayList<Menu> readMenusFromDB(Connection conn) {

        String sql = "SELECT count(DISTINCT menuID) FROM menuTypes";
        int menucount = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            menucount = rs.getInt(1);
//            System.out.println(menucount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Menu> dbMenuArrayList = new ArrayList<Menu>(menucount);

        sql = "SELECT mt.menuID,mt.name,mt.menuTypeID\n"
                + ",COALESCE(cm.cost, om.cost) cost,COALESCE(cm.frequency, om.frequency) frequency,COALESCE(cm.recency, om.recency) recency\n"
                + ",cm.cookingtime,cm.cookingdifficulty\n"
                + ",om.foodcost,om.deliverycost,om.deliveryservicename\n"
                + "FROM menuTypes mt\n"
                + "LEFT JOIN orderedMenus om ON mt.menuID = om.menuID\n"
                + "LEFT JOIN cookedMenus cm ON mt.menuID = cm.menuID\n"
                + "ORDER BY mt.menuID ASC";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int menuTypeID = rs.getInt("menuTypeID");
                switch (menuTypeID)
                    {
                        case 1: // 1: CookedMenu
                            CookedMenu cm = new CookedMenu(rs.getString("name"),
                            rs.getDouble("cost"),
                            rs.getInt("frequency"),
                            rs.getInt("recency"),
                            rs.getDouble("cookingtime"),
                            rs.getDouble("cookingdifficulty"));

                            dbMenuArrayList.add(cm);

                            break;
                        case 2: // 2. OrderedMenu
                            OrderedMenu om = new OrderedMenu(rs.getString("name"),
                                    rs.getDouble("cost"),
                                    rs.getInt("frequency"),
                                    rs.getInt("recency"));

                            om.setFoodCost(rs.getDouble("foodcost"));
                            om.setDeliveryCost(rs.getDouble("deliverycost"));
                            om.setDeliveryService(rs.getString("deliveryservicename"));

                            dbMenuArrayList.add(om);


                            break;
                        default:
                            System.out.println("This should never happen. Please investigate!");
                            break;
                    }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();}

        return dbMenuArrayList;
    }
}