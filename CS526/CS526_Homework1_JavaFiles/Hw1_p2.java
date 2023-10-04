import spring2021.penny.Car;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Hw1_p2 {


	public static void findByMake(Car[] cars, String make) {
    /*
    This method iterates through an array of Car objects and checks if any Car objects are of a specified make.
    During the iteration, if a Car object is a match for the make provided, its details are printed to the console
    Input arguments: This method takes 2 arguments. An array of Car objects (to look into) and a String (to match with each Car's make attribute)
    Output arguments: This method does not return anything, but prints messages via console.
    */
		// implement this method
        // Counter to keep track of the number of cars matched with the make argument entered
        int makeMatchCounter = 0;
        // iterates through the array of Cars
        for (int i =0; i < cars.length; i++) {
            // checks if the make attribute of Car at index i is equal to the make argument
            if (cars[i].getMake().equals(make)) {
                // If the make argument matches the make of the car, the Car object is printed
                System.out.println(cars[i]);
                // the counter for cars that matches the make argument is incremented by 1
                makeMatchCounter++;
            }
        }
        // If no matches were found, print a message that says so.
        if (makeMatchCounter == 0) {System.out.println("No "+make+" cars found!");}
	}



	public static void olderThan(Car[] cars, int year) {
    /*
    This method iterates through an array of Car objects and checks if any Car objects' year attribute is smaller than the year argument provided
    During the iteration, if a Car object's year attribute is smaller than the year argument provided, its details are printed to the console
    Input arguments: This method takes 2 arguments. An array of Car objects (to look into) and an int (to match with each Car's year attribute)
    Output arguments: This method does not return anything, but prints messages via console.
    */
		// implement this method
        int yearMatchCounter = 0;
        for (int i =0; i < cars.length; i++) {
            Car carToExamine = cars[i];
            if (carToExamine.getYear() < year) {
                System.out.println(carToExamine);
                yearMatchCounter++;
            }
        }
        if (yearMatchCounter == 0) {System.out.println("No cars made before "+year+" found!");}
	}

	public static void main(String[] args) throws IOException {
        // complete this part
		// create an array of Car objects, cars, of size 10
		// read input file and store 10 car Objects in the array
        // Import the file to build car objects with.
        // First make a file objects with the file provided.
        File carFile = new File("car_input.txt");
        // Then use scanner to read said file.
        Scanner carScan = new Scanner(carFile);

        // Right now the length of the cars array is predetermined to be 10.
        int carCount = 10;
        // Make the cars array.
        Car[] cars = new Car[carCount];

        // Counter helper variable to place each car object created into the cars array.
        int arraylocation = 0;
        // We use the Scanner.hasNext() method to parse through the file to the end.
        // Right now the file has 10 lines, so everything works.
        // In the real world,
        while(carScan.hasNext()) {
            // Take in a line from the file.
            String incomingCarString = carScan.nextLine();
            // Split the row string to its comma-delimited comments
            String[] incomingCarArray = incomingCarString.split(",");
            // Trim the whitespace away and create a car object from the appropriate components
            Car incomingCar = new Car(incomingCarArray[0].trim()
                    , Integer.valueOf(incomingCarArray[2].trim())
                    , Integer.valueOf(incomingCarArray[1].trim())
            );
            // add the new car object to the cars array.
            cars[arraylocation] = incomingCar;
            // increment the array index location tracker by 1
            arraylocation++;
        }

        // Close the scanner.
        carScan.close();

        // Print all car objects.
        System.out.println("\nAll cars:");
		for (int i=0; i<cars.length; i++) {
			System.out.println(cars[i]);
		}

        // arguments to use in findByMake and olderThan methods.
		String make = "Honda";
		int year = 2017;

        // Find all Hondas. There are 2.
		System.out.println("\nAll cars made by " + make);
		findByMake(cars, make);
        // Find all cars made before 2017. There are 6.
		System.out.println("\nAll cars made before " + year);
		olderThan(cars, year);
	}

}
