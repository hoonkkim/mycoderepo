package com.company;

public class IntToString {
    public String Digits(Integer x)
    {
        if(x < 10) {return x.toString();}

        // all the helper variables to parse without parse
        // convert to string to get length
        String y = x.toString();
        int length = y.length();
        // get the divisor for isolating the top digit
        Integer a = (int)Math.pow(10, length-1);
        // Divide. Keep this as building blocks for the output
        Integer b = x / a;
        // Modulo for the recursion.
        Integer c = x % a;

        // Testing
        // System.out.println("length:"+length+" divisor:"+a+" top digit:"+b+" remainder:"+c);

        // Output is quotient + Digits(modulo) ad nauseum
        String output = b.toString()+Digits(c);
        return output;
    }

}
