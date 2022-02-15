package Menus;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Menu implements Comparable<Menu>, Serializable {
    // abstract class for all menu items. Cooked & Ordered will be concrete meals.
    // Attributes and Methods are ordered A to Z
    // Attributes

    private double cost = -1;
    // 30 day frequency
    private int frequency = -1;
    // Number of Days since last eaten.
    private int recency = -1;
    private String name = "DefaultName";
    private double score = -1;
    private String type = "If you see this you have hit a bug.";
    private Date mostRecentUpdateDate = parseDate("01/01/1900");

    static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    Menu() {}
    // Methods
    // Basic getters and setters for all menus
    // need to roll all common methods up to the abstract class's method
    public double getCost() {return cost;}
    public int getFrequency() {return frequency;}
    public int getRecency() {return recency;}
    public String getName() {return name;}
    public double getScore() {return score;}
    public String getType() {return type;}
    public Date getMostRecentUpdateDate() {return mostRecentUpdateDate;}

    public void setCost(double costInput) {cost = costInput;}
    public void setFrequency(int frequencyInput) {frequency = frequencyInput;};
    public void setRecency(int recencyInput) {recency = recencyInput;}
    public void setName(String nameInput) {name = nameInput;}
    public void setScore(double scoreInput) {score = scoreInput;}
    public void setMostRecentUpdateDate(Date dateInput) {mostRecentUpdateDate = dateInput;}

    public int compareTo(Menu menuToCompare) {
        if (getScore() > menuToCompare.getScore()) {
            return 1;
        }
        if (getScore() == menuToCompare.getScore()) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isSame(Menu menuToCompare) {
        if(
            Objects.equals(this.getName(), menuToCompare.getName()) &
            this.getCost() == menuToCompare.getCost() &
            Objects.equals(this.getType(), menuToCompare.getType())
        )
          {return true;}
        else
            {return false;}
    }

}
