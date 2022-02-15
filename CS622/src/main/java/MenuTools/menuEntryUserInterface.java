package MenuTools;

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

        System.out.println("Please Enter the Cost of your Menu.");
        Double costEntry = userInputScanner.nextDouble();
        MenuAttributeContainer<Double> menuCostContainer = new MenuAttributeContainer(costEntry);
        System.out.println("Your "+menuNameContainer.getAttribute()+" cost "+menuCostContainer.getAttribute());

        System.out.println("Please Enter How many times you ate this menu in the last 30 days.");
        int frequencyEntry = userInputScanner.nextInt();
        MenuAttributeContainer<Integer> menuFrequencyContainer = new MenuAttributeContainer(frequencyEntry);
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
            } catch (ParseException e) {
//                e.printStackTrace();
                System.out.println("What you entered is not a valid date!");
            }
            if(recencyEntryDate != null) {dateEntryCorrect = true;}

        }
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        // Debug
//        System.out.println(currentDate.getTime());
//        System.out.println(recencyEntryDate.getTime());
        long datediff = currentDate.getTime()-recencyEntryDate.getTime();
//        System.out.println(datediff);
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

                    System.out.println("Please enter the cooking difficulty for " + menuNameContainer.getAttribute() + " on a scale from 1 to 10.");
                    double menuDifficultyInput = userInputScanner.nextDouble();
                {
                    newCookedMenu.setCookingDifficulty(menuDifficultyInput);
                }

                System.out.println("Please enter the cooking time for " + menuNameContainer.getAttribute() + " in minutes");
                double menuCookingTimeInput = userInputScanner.nextDouble();
                newCookedMenu.setCookingTime(menuCookingTimeInput);

                System.out.println("Your Menu has been logged successfully!");
                return newCookedMenu;


                case "2":
                    System.out.println("Thanks. Lets continue with details for an Ordered Menu");
                    OrderedMenu newOrderedMenu = new OrderedMenu(
                            menuNameContainer.getAttribute(), menuCostContainer.getAttribute(),
                            menuFrequencyContainer.getAttribute(), menuRecencyContainer.getAttribute());

                    System.out.println("Please enter the Food Cost for " + menuNameContainer.getAttribute());
                    double foodCostInput = userInputScanner.nextDouble();
                    newOrderedMenu.setFoodCost(foodCostInput);

                    System.out.println("Please enter the Delivery Cost for " + menuNameContainer.getAttribute());
                    double deliveryCostInput = userInputScanner.nextDouble();
                    newOrderedMenu.setDeliveryCost(deliveryCostInput);

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
