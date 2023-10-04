package com.company;

public class Asterisks {

    public String Asterisks(int n) {
        if(n == 1) {return "*";}
        // String builder with a loop. I could recurse...
        String Output = "*"+Asterisks(n-1);
        return Output;
    }


    public void printNum(int cur, int end) {
        if (cur == end+1) {
            System.out.println((Asterisks(cur-1)));
            return;
        }
        System.out.println(Asterisks(cur));
        printNum(cur+1, end);
        System.out.println(Asterisks(cur));

    }
}

