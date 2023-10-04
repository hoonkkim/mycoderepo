
import java.util.Arrays;

public class Hw1_p1 {


	public static void find(int[] a, int x) {
		/*
			This method searches an array of ints a to determine if int x (input) exists.
			If x exists in a, this method prints the index location where x was found.
			If x does not exist in a, this method prints a message that x was not found.
			Input arguments: This method takes 2 arguments. An array of ints (to look into) and an int (to look for)
			Output arguments: This method does not return anything, but prints messages via console.
		*/
		int xcounter = 0; // Tracks how many x's were found in a

		// For-loops through a from the first to last element.
		for (int i = 0; i< a.length; i++) {
			// every time an int found at index i of array a equals x
			if (a[i] == x) {System.out.println(x+" found at index "+i); // this message is printed
				xcounter += 1;} // counter for number of x's found goes up by 1
		}
		if(xcounter == 0) // if counter is 0, no x's were found
		{System.out.println(x+" does not exist.");} // Then message that no x's were found is printed
	}


	public static boolean isPrefix(String s1, String s2) {
	/*
    This method searches an array of ints a to determine if int s1 is a prefix of s1.
	This method takes the length of s1, then takes the substring of the same length from s2.
	Then the method compares the two strings. If the two are equal, s1 is a substring of s2.
    Input arguments: This method takes 2 arguments. String s1 is the prefix to parse s2 for. s2 is the string to be parsed.
    Output arguments: This method returns a boolean value. True if s1 is a subset of s2, and False if otherwise.
	*/
		// Substring of s1 the length of s1 is taken for comparison.
		String s2subset = s2.substring(0,s1.length());
		// Uses the equals method to compare s1 to the subset of s2 of equal length
		boolean isSubset = s2subset.equals(s1);
		// If the two strings are equal, then s1 is a prefix of s2, and true is returned. If otherwise, false is returned.
		return isSubset;
	}
	
	/* The main method is used to run the methods implemented above */
	public static void main(String[] args) {
		
		int[] a = {5, 3, 5, 6, 1, 2, 12, 5, 6, 1};

		// Checks if 5 exists in a. It does 3 times.
		find(a, 5);
		// Checks if 10 exists in a. It doesn't.
		find(a, 10);
		System.out.println();

		// Creates the prefix to look for and the strings to parse.
		String s1 = "abc";
		String s2 = "abcde";
		String s3 = "abdef";

		// Checks if abc is a prefix of abcde. It is.
		if (isPrefix(s1,s2)) {
			System.out.println(s1 + " is a prefix of " + s2);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s2);
		}
		// Checks if abc is a prefix of abdef. It isn't.
		if (isPrefix(s1,s3)) {
			System.out.println(s1 + " is a prefix of " + s3);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s3);
		}
	}
}
