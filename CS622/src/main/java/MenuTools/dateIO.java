package MenuTools;

import Menus.Menu;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class dateIO {

    static LocalDate currentDate= java.time.LocalDate.now();
    static LocalDate lastDate;

    public static int getDaysSinceLastDate() {
        int dateDiff;
        if(lastDate == null) {dateDiff = 0;}
        else {dateDiff = Period.between(currentDate,lastDate).getDays();}
        return dateDiff;
    }

    public static void exportCurrentDate() {
        try {
            FileOutputStream f = new FileOutputStream(new File("dateData.dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            try {
                o.writeObject(currentDate);
                o.close();
                f.close();
                } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void importLastDate() {
        try {
            FileInputStream fis = new FileInputStream("dateData.dat");
            ObjectInputStream input = new ObjectInputStream(fis);
            lastDate = (java.time.LocalDate) input.readObject();
        }
        catch (FileNotFoundException e) {
            System.out.println("Looks like we don't have a datafile for dates. We'll make one at the end.");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
