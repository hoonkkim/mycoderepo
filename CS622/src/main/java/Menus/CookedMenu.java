package Menus;

import Menus.Menu;

import java.io.Serializable;

public class CookedMenu extends Menu implements Serializable{
    // Menus.CookedMenu Specific Attributes
    // cookingDifficulty is on a scale of 1-10
    private double cookingDifficulty = 0;
    // cookingTime is minutes
    private double cookingTime = 0;

    // Constructors: 1 empty, 1 full
    public CookedMenu() {}
    public CookedMenu(String nameInput, double costInput, int frequencyInput, int recencyInput) {
        super.setName(nameInput);
        super.setCost(costInput);
        super.setFrequency(frequencyInput);
        super.setRecency(recencyInput);}

    public CookedMenu(String nameInput, double costInput, int frequencyInput
            , int recencyInput, double cookingDifficultyInput, double cookingTimeInput) {
        super.setName(nameInput);
        super.setCost(costInput);
        super.setFrequency(frequencyInput);
        super.setRecency(recencyInput);
        this.cookingDifficulty = cookingDifficultyInput;
        this.cookingTime = cookingTimeInput;
    }

    // Menus.CookedMenu specific Methods

    public String getType() {return "Cooked";}
    // Cooking time & Cooking difficulty getters & setters
    public double getCookingDifficulty() { return cookingDifficulty; }
    public double getCookingTime() { return cookingTime; }
    public void setCookingTime(double timeInput) {
        cookingTime = timeInput;
    }
    public void setCookingDifficulty(double difficultyInput) {
        cookingDifficulty = difficultyInput;
    }

}
