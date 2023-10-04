package ProgramInterface;

import Menus.CookedMenu;
import Menus.Menu;
import Menus.MenuAttributeContainer;
import Menus.OrderedMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class menuEntryUserInterface {
// This class provides a console interface for manually entering a menu via console
    public Menu menuEntry()
    {
        Scanner userInputScanner = new Scanner(System.in);

        System.out.println("Hello, welcome to menu entry");

        // Common Attributes
        // Need more exceptions catching for entries that do not match attribute types.
        System.out.println("Please Enter the Name of your Menu.");
        MenuAttributeContainer<String> menuNameContainer = new MenuAttributeContainer(userInputScanner.nextLine());
        System.out.println("The Menu you entered is: "+menuNameContainer.getAttribute());



        boolean correctCostInput = false;
        MenuAttributeContainer<Double> menuCostContainer = new MenuAttributeContainer(0.0);
        while(!correctCostInput) {
            System.out.println("Please Enter the Cost of your Menu.");
            String costInputString = userInputScanner.nextLine();
            try {
                Double costEntry = Double.parseDouble(costInputString);
                menuCostContainer.setAttribute(costEntry);
                correctCostInput = true;
                System.out.println("Your "+menuNameContainer.getAttribute()+" cost "+menuCostContainer.getAttribute());
                break;
            } catch (Exception e) {
                System.out.println("Incorrect format was entered, please try again");
            }
        }


        boolean correctFrequencyInput = false;
        MenuAttributeContainer<Integer> menuFrequencyContainer = new MenuAttributeContainer(0);
        while(!correctFrequencyInput) {
            System.out.println("Please Enter How many times you ate this menu in the last 30 days.");
            String frequencyEntry = userInputScanner.nextLine();
            try {
                Integer frequencyInputInteger = Integer.parseInt(String.valueOf(frequencyEntry));
                menuFrequencyContainer.setAttribute(frequencyInputInteger);
                correctFrequencyInput = true;
                System.out.println("Your "+menuNameContainer.getAttribute()+" cost "+menuFrequencyContainer.getAttribute());
                break;
            } catch (Exception e) {
                System.out.println("Incorrect format was entered, please try again");
            }
        }

        System.out.println("You ate "+menuNameContainer.getAttribute()+" "+menuFrequencyContainer.getAttribute()+" times in the last 30 days");


        String recencyEntry;
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        boolean dateEntryCorrect = false;
        Date recencyEntryDate = null;

        while(dateEntryCorrect == false) {
            System.out.println("Please Enter the date you ate this menu in MM/DD/YYYY format.");
            recencyEntry = userInputScanner.nextLine();
            try {
                recencyEntryDate = inputFormat.parse(recencyEntry);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("What you entered is not a valid date!");
            }
            if(recencyEntryDate != null) {dateEntryCorrect = true;}

        }

        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        // Debug
        long datediff = currentDate.getTime()-recencyEntryDate.getTime();
        long recencyDays = TimeUnit.MILLISECONDS.toDays(datediff);
        Integer recencyDaysInt = (int)recencyDays;
        MenuAttributeContainer<Integer> menuRecencyContainer = new MenuAttributeContainer(recencyDaysInt);
        System.out.println("You ate " + menuNameContainer.getAttribute()+" "+menuRecencyContainer.getAttribute()+" days ago");

        while(true) {
            System.out.println("Enter 1 if your menu was cooked and 2 if it was ordered");
            String orderedVsCooked = userInputScanner.nextLine();

            switch (orderedVsCooked) {
                case "1":
                    System.out.println("Thanks. Lets continue with details for a Cooked Menu");
                    CookedMenu newCookedMenu = new CookedMenu(
                            menuNameContainer.getAttribute(), menuCostContainer.getAttribute(),
                            menuFrequencyContainer.getAttribute(), menuRecencyContainer.getAttribute());


                    // Fix

                boolean correctCookingDifficultyInput = false;
                while(!correctCookingDifficultyInput) {
                    System.out.println("Please enter the cooking difficulty for " + menuNameContainer.getAttribute() + " on a scale from 1 to 10.");
                    String cookingDifficultyInputString = userInputScanner.nextLine();
                    try {
                        double cookingDifficultyInputDouble = Double.parseDouble(cookingDifficultyInputString);

                        if(cookingDifficultyInputDouble >= 0 & cookingDifficultyInputDouble <= 10) {
                            correctCookingDifficultyInput = true;
                            newCookedMenu.setCookingDifficulty(cookingDifficultyInputDouble);
                            break;
                        }
                        else {System.out.println("You entered an invalid value for difficulty");}
                    } catch (Exception e) {
                        System.out.println("Incorrect format was entered, please try again");
                    }
                }


                    // Fix

                boolean correctCookingTimeInput = false;
                while(!correctCookingTimeInput) {
                    System.out.println("Please enter the cooking time for " + menuNameContainer.getAttribute() + " in minutes");
                    String cookingTimeInputString = userInputScanner.nextLine();
                    try {
                        double cookingTimeInputDouble = Double.parseDouble(cookingTimeInputString);
                        correctCookingTimeInput = true;
                        newCookedMenu.setCookingTime(cookingTimeInputDouble);
                        break;
                    } catch (Exception e) {
                        System.out.println("Incorrect format was entered, please try again");
                    }
                }
                    System.out.println("Your Menu has been logged successfully!");
                    return newCookedMenu;


                case "2":
                    System.out.println("Thanks. Lets continue with details for an Ordered Menu");
                    OrderedMenu newOrderedMenu = new OrderedMenu(
                            menuNameContainer.getAttribute(), menuCostContainer.getAttribute(),
                            menuFrequencyContainer.getAttribute(), menuRecencyContainer.getAttribute());

                    // Food Cost Entry
                    boolean correctFoodInputCost = false;
                    while(!correctFoodInputCost) {
                        System.out.println("Please enter the Food Cost for " + menuNameContainer.getAttribute());
                        String foodCostInputString = userInputScanner.nextLine();
                        try {
                            double foodCostInputDouble = Double.parseDouble(foodCostInputString);
                            correctFoodInputCost = true;
                            newOrderedMenu.setFoodCost(foodCostInputDouble);
                            break;
                        } catch (Exception e) {
                            System.out.println("Incorrect format was entered, please try again");
                        }
                    }

                    // Delivery Cost Entry
                    boolean correctDeliveryInputCost = false;
                    while(!correctDeliveryInputCost) {
                        System.out.println("Please enter the Delivery Cost for " + menuNameContainer.getAttribute());
                        String deliveryCostInputString = userInputScanner.nextLine();
                        try {
                            double deliveryCostInputDouble = Double.parseDouble(deliveryCostInputString);
                            correctDeliveryInputCost = true;
                            newOrderedMenu.setFoodCost(deliveryCostInputDouble);
                            break;
                        } catch (Exception e) {
                            System.out.println("Incorrect format was entered, please try again");
                        }
                    }


                    System.out.println("Please enter the Delivery Service name for " + menuNameContainer.getAttribute());
                    String deliveryServiceInput = userInputScanner.nextLine();
                    newOrderedMenu.setDeliveryService(deliveryServiceInput);

                    System.out.println("Your Menu has been logged successfully!");
                    return newOrderedMenu;


                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

}
