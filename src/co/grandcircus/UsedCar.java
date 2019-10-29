package co.grandcircus;

public class UsedCar extends Car {

	private double mileage;
	
	public UsedCar(String make, String model, int year, double price, double mileage) {
		super(make, model, year, price);
		this.mileage = mileage;
		// TODO Auto-generated constructor stub
	}

	public UsedCar() {
		super();
	}
	
	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return super.toString() + " | Mileage: " + mileage;
	}
	
	



}
