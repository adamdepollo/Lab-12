package co.grandcircus;

public class CarsIO {

	public Car getCar(int index) {
		switch (index) {
		case 1:
			return new UsedCar("Nissan", "Rogue", 2015, 15000, 40000);
		case 2:
			return new UsedCar("Honda", "Civic", 2012, 10000, 65000);
		case 3:
			return new UsedCar("Chevrolet", "Monte Carlo", 2004, 3500, 115000);
		case 4:
			return new Car("Nissan", "Altima", 2020, 24000);
		case 5:
			return new Car("Jeep", "Compass", 2020, 26500);
		case 6:
			return new Car("Chevrolet", "Volt", 2020, 30000);
		default:
			return null;
		}
	}

}
