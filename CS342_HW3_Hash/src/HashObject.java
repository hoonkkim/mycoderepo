
public class HashObject {
    private String data;
    private int key;

    private boolean used;

    public String getData() {
        return data;
    }
    public void setData(String data) {this.data = data;
    }
     public int getKey() {return key;}
     public void setKey(int k) {this.key = k;}



    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean validity) {
        this.used = validity;
    }

}
