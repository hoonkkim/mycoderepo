package Settings;

import java.io.Serializable;

public abstract class user implements Serializable {
    public String userID;
    public String getID() {return userID;}

    public String getCurrency() {
        return null;
    }
}

