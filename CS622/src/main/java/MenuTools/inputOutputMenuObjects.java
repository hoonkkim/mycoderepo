package MenuTools;
import Menus.CookedMenu;
import Menus.Menu;
import Menus.OrderedMenu;

import java.io.*;
import java.util.ArrayList;

public class inputOutputMenuObjects {

    public void exportMenuObject(ArrayList<Menu> m) throws IOException {
    try {
        FileOutputStream f = new FileOutputStream(new File("menusInUse.dat"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        m.forEach((menu) -> {
            try {
                o.writeObject(menu);
                System.out.println(menu.getName()+" was output to file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        o.close();
        f.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found");
    }
};


    public ArrayList<Menu> importMenuObject(ArrayList<Menu> m) {
        try {
            FileInputStream fis = new FileInputStream("menusInUse.dat");
            ObjectInputStream input = new ObjectInputStream(fis);
            boolean x = true;
            while (x) {
                Object obj = input.readObject();
                if(obj != null) {m.add((Menu) obj);}
                else {x=false;}
//                System.out.println((Menu) obj);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Looks like we don't have a datafile. We'll make one at the end.");
        }
        catch (EOFException e) {
            System.out.println("Read " + m.size() + " Menu Objects!");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    return m;
    }
}
