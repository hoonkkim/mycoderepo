package com.example.demo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        String[] sa = {"data", "structures", "and", "algorithms"};
//        String[] sb = sa;
//        Arrays.sort(sb); //sort strings in the array in alphabetical order
//        String[] sc = {"data", "structures", "and", "algorithms"};
//        String[] sd = sc.clone();
//        Arrays.sort(sd); //sort strings in the array in alphabetical order
//
//        System.out.println(sa[0]);
//        System.out.println(sc[0]);

//        int x = 10; // initialize x
//        int y = 20; // initialize y
//// execute the following loop block 5 times
//// beginning of loop block
//        for (int i=0; i < 6; i++){
//            x = y + 5;
//            y = x - 1;
//            x = 2 * y;
//            y = y / 2; // integer division
//            System.out.println("x is: " + x);
//            System.out.println("y is: " + y);
//        }
// end of loop block

        int i = 0;
        int j = 0;
        while (i <= 10) {
            j = j + 1;
            i = i + 1;
        }

//        System.out.println(i);
//        System.out.println(j);

        int[] mtestarray = new int[4];
        mtestarray[0] = 1;
        mtestarray[1] = 1;
        mtestarray[2] = 1;
        mtestarray[3] = 1;
        method4(mtestarray, 0);

    }

    static int method4(int[] a, int n) { // this method returns an integer
        if (n < 1) return a[n];
        else {
            int x = n / 2;
            int i = 0;
            int y = 0;
            while (i < a.length) {
                y = y + a[i];
                i = i + 1;
                System.out.println(i);
            }
            return method4(a, x);
        }
    }
}
