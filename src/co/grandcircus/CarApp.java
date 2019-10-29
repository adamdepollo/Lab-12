/*
 * @Adam DePollo
 */
package co.grandcircus;

import java.util.ArrayList;
import java.util.Scanner;

public class CarApp {

	public static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		// Declare variables
		String cont = "yes";
		ArrayList<Car> carList = new ArrayList<>();
		CarsIO cars = new CarsIO();

		// For loop populates carList with the cars saved in CarsIO
		for (int i = 1; i <= 6; i++) {
			carList.add(cars.getCar(i));
		}

		// Welcome user
		System.out.println("Welcome to the Grand Circus Motors Admin Console!");

		// Continue loop while user decides to continue
		while (cont.equalsIgnoreCase("yes")) {
			// Take input for the action they'd like to take and call appropriate methods
			// based on their choice
			switch (Validator.getInt(scnr,
					"What would you like to do (enter the number):\n1. Add inventory\n2. Purchase inventory\n")) {
			case 1:
				int numCars = Validator.getInt(scnr, "How many cars would you like to enter?\n", 1, Integer.MAX_VALUE);
				carList = makeCar(scnr, numCars, carList);
				displayInventory(carList);
				break;
			case 2:
				purchaseInventory(carList);
				break;
			}

			//Ask user if they want to continue
			System.out.println("Do you want to take another action? (yes/no)");
			cont = scnr.nextLine();
		}

		//Say bye and close scanner
		System.out.println("Bye!");
		scnr.close();
	}

	// This method allows user to add as many Car objects to the carList as they
	// stated they wanted to enter in the main method.
	public static ArrayList<Car> makeCar(Scanner scnr, int numCars, ArrayList<Car> carList) {
		// For loop repeats a number of times equals to the number of cars the user
		// wants to enter
		for (int i = 1; i <= numCars; i++) {
			// Get input for make, model, year, price, and whether the car is used
			System.out.println("Enter car #" + i + " make:");
			String make = scnr.nextLine();
			System.out.println("Enter car #" + i + " model:");
			String model = scnr.nextLine();
			int year = Validator.getYear(scnr, "Enter car #" + i + " year (yyyy):\n");
			double price = Validator.getDouble(scnr, "Enter car #" + i + " price: ", 0.0, Double.MAX_VALUE);
			System.out.println("Is car #" + i + " used? (yes/no)");
			String used = Validator.getCont(scnr);
			if (!used.equalsIgnoreCase("yes")) { // If the car is not used ...
				carList.add(new Car(make, model, year, price)); // ... add it to the carList as a Car object
			} else {// ...otherwise, get user to input mileage and add it to the carList as a
					// UsedCar object
				double mileage = Validator.getDouble(scnr, "Enter car #" + i + " mileage: ", 1, Double.MAX_VALUE);
				carList.add(new UsedCar(make, model, year, price, mileage));
			}
		}
		return carList;
	}

	// This method displays a formatted version of the current carList
	public static void displayInventory(ArrayList<Car> carList) {
		int modelLength = 0;
		int makeLength = 0;
		int priceLength = 0;
		int counter = 1;

		// For loop finds the longest model, make, and price on the list to use in
		// formatting
		for (Car c : carList) {
			if (c.getMake().length() > makeLength) {
				makeLength = c.getMake().length();
			}
			if (c.getModel().length() > modelLength) {
				modelLength = c.getModel().length();
			}
			if (Double.toString(c.getPrice()).length() > priceLength) {
				priceLength = Double.toString(c.getPrice()).length();
			}
		}

		// Print a header and divider
		System.out.println("Current Inventory");
		for (int i = 0; i < makeLength + priceLength + modelLength + 80; i++) {
			System.out.print("*");
		}

		// Print the formatted inventory
		System.out.print("\n");
		for (Car c : carList) {
			System.out.printf("%-3d | " + c + "\n", counter);
			counter++;
		}
	}

	// This method allows the user to select inventory to purchase and removes it
	// from the list
	public static void purchaseInventory(ArrayList<Car> carList) {
		String cont = "";
		// Loop until user confirms the inventory they want to remove
		do {
			// Call method to display inventory and the get the user's input for which car
			// they want to purchase using the number of the vehicle
			displayInventory(carList);
			int purchaseChoice = Validator.getInt(scnr, "\nWhich car would you like to purchase? (select the number)\n",
					1, carList.size());

			// Confirm user's choice using Validator.getCont
			System.out.println("Is this the car you would like to purchase? (yes/no)");
			System.out.println(carList.get(purchaseChoice - 1));
			cont = Validator.getCont(scnr);

			// If user confirms, congratulate them and remove the purchased car from the
			// inventory
			if (cont.equalsIgnoreCase("yes")) {
				carList.remove(purchaseChoice - 1);
				System.out.println("Congratulations on your sale! The inventory has been removed from the system.");
			}

			// Otherwise, restart loop
			else {
				System.out.println("OK, let's try again");
				cont = "";
			}
		} while (cont.equalsIgnoreCase(""));
	}

}
