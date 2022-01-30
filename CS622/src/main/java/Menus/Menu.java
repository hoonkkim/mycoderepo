package Menus;

public abstract class Menu implements Comparable<Menu>{
    // abstract class for all menu items. Cooked & Ordered will be concrete meals.
    // Attributes and Methods are ordered A to Z
    // Attributes
    private double cost = -1;
    private int frequency = -1;
    private int recency = -1;
    private String name = "DefaultName";

    // Methods
    // Basic getters and setters for all menus
    // need to roll all common methods up to the abstract class's method
    public double getCost() {return cost;}
    public int getFrequency() {return frequency;}
    public int getRecency() {return recency;}
    public String getName() {return name;}

    public void setCost(double costInput) {cost = costInput;}
    public void setFrequency(int frequencyInput) {frequency = frequencyInput;};
    public void setRecency(int recencyInput) {recency = recencyInput;}
    public void setName(String nameInput) {name = nameInput;}

    public int compareTo(Menu menuToCompare) {
        if (getFrequency() > menuToCompare.getFrequency()) {
            return 1;
        }
        if (getFrequency() == menuToCompare.getFrequency()) {
            return 0;
        } else {
            return -1;
        }
    }
}
