package com;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
//        Lets start with getting our random number.
//        We'll use Math.random()
        Double NumbertoGuessDouble = Math.random();
//        Math.random gives us a double but we want an int for finite guessing
//          This will give us 0-1000 as random generates between 0-1
//          Convert by *1000 then forcing it to an int
        NumbertoGuessDouble = NumbertoGuessDouble*1000;
        int NumbertoGuessInt = NumbertoGuessDouble.intValue();

//        This is for debugging - making sure this works correctly.
        String OutputString = new String("Your Number is: "+NumbertoGuessInt);

        System.out.println(OutputString);

// Solicit user input

        System.out.println("Hello, I would like you to guess an integer between 1 and 1000");
        Scanner userKeyboardInput = new Scanner(System.in);
        String userInput = userKeyboardInput.nextLine();

        System.out.println(userInput);

// See if user input is an int.
// If it is non-numeric, send back.
// If it is Out of Bounds, send back.
// If it is in-bounds non-int numeric, force it to be an int then proceed



    }
}
