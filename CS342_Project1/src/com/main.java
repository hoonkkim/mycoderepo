package com;

import java.util.Scanner;

public class main {

public static String numericOnly(String toParse) {
        toParse = toParse.replaceAll("[^0-9]", "");
        return toParse;
    }

    public static void main(String[] args) {
//        Lets start with getting our random number.
//        We'll use Math.random()
        Double NumbertoGuessDouble = Math.random();
//        Math.random gives us a double but we want an int for finite guessing
//        This will give us 0-999 as random generates between 0-0.99999
//        Convert by *1000 then forcing it to an int. Need to add 1 so we don't get 0 and do get 1000.

        NumbertoGuessDouble = NumbertoGuessDouble*1000+1;
        int NumbertoGuessInt = NumbertoGuessDouble.intValue();

//        This is for debugging - making sure this works correctly.
//        String OutputString = new String("Your Number is: "+NumbertoGuessInt);
//        System.out.println(OutputString);

// Solicit user input

int attemptcounter = 0;
int parsedUserInputInt = -9000091; // Random value here that will break if nothing is entered.

int lowerbound = 1;
int upperbound = 1000;
boolean correctnumberfound = false;
// Loop Here.
        System.out.println("I would like you to guess an integer I have selected which is between 1 and 1000");
        System.out.println("Any decimal, negative, non-numeric inputs will be rejected.");
        while(!correctnumberfound) {

           Scanner userKeyboardInput = new Scanner(System.in);
            String userInput = userKeyboardInput.nextLine();

            // println for debugging, to see what the user actually entered (vs what we parsed/passed)
            System.out.println("You have entered ");
            System.out.println(userInput);

            //Use Regex to Delete any a. non-numerics, b. negative signs, c.mixes (decimals, etc), d. OOB positive (1001 and above)
            String parsedUserinput = numericOnly(userInput);

            // println for debugging, to track post parsed input.
            //            System.out.println(parsedUserinput);

            // First If parses the string entry to make sure it is a positive integer. (negative sign/string/decimal gets filtered out. Blank entries also trip the if/else.)
            if(parsedUserinput.contentEquals(userInput) && userInput.length() > 0)
            {
                // Now that we've cleared the non-ints. We can assign the entered value to the int variable used to compare.

                parsedUserInputInt = Integer.parseInt(parsedUserinput);
                // Second if makes sure that the int entry is between 1 and 1000. If the entry is valid (within range & correct entry type), add to counter.
                if(parsedUserInputInt >= lowerbound && parsedUserInputInt <= upperbound) {
                    parsedUserInputInt = Integer.parseInt(userInput);
                    attemptcounter = attemptcounter + 1;

                    // Third if compares the entry to the RNG'd number and gives an output. Larger & smaller makes no changes. Tells user up/down. Correct output flips boolean value to end loop.
                  if(parsedUserInputInt < NumbertoGuessInt) {
                      System.out.println("Your guess is lower than the answer. Try Let's try again.");
                  }
                  else if (parsedUserInputInt > NumbertoGuessInt) {
                      System.out.println("Your guess is higher than the answer. Try Let's try again.");
                  }
                  else {
                      System.out.println("Congratulations! You have guessed the correct number, "+NumbertoGuessInt+"!");
                      System.out.println("It took you "+attemptcounter+" guesses.");
                      correctnumberfound = true;
                  }

                }
                // OOB error message
                else
                {
                    System.out.println("Your entry is out of bounds. Let's try again.");
                }

            }
            // Invalid entry error message.
            else
            {
                System.out.println("You've entered an invalid character. Let's try again.");
            }

        }

        // Success!
        System.out.println("Thank you for playing the number guessing game! Bye!");
}
}
