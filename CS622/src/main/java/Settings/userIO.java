package Settings;

import java.io.*;

public class userIO {
    public user importUser(String userID) throws IOException {
        user u = null;
        try {
            FileInputStream fis = new FileInputStream(userID + ".dat");
            ObjectInputStream input = new ObjectInputStream(fis);
            u = (user) input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return u;
    }

    public void exportUser(user U) {
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(new File(U.getID()+".dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(U);
            o.close();
            f.close();
            System.out.println("User Setting was output to "+U.getID()+".dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
