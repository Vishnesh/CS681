package edu.umb.cs681.hw20;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private String model, make;
	private int mileage, year;
	private int price;
	private int dominationCount;

	public Car(String make, String model, int mileage, int year, int price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public String getMake() {
		return make;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}

	public void setDominationCount(ArrayList<Car> cars) {
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--;
	}

	public String toString() {
		return this.make + " " + this.model + " " + this.mileage + " " + this.year + " " + this.price;
	}

	public static void main(String[] args) {
		List<Car> list = new ArrayList<Car>();
		list.add(new Car("Porsche", "Cayenne", 19, 2003, 67500));
		list.add(new Car("BMW", "M5", 15, 1985, 103500));
		list.add(new Car("Audi", "A6", 23, 2004, 54900));

		System.out.println("Counting Cars using Reduce");
		long carCount = list.stream().map((Car car) -> car.getModel()).count();
		System.out.println("Car count: " + carCount);

		System.out.println("Counting Cars using 3rd version of Reduce");
		int carModelNum = list.stream().map((Car car) -> car.getModel()).reduce(0, (result, carModel) -> ++result,
				(finalResult, intermediateResult) -> finalResult);
		System.out.println("Car count: " + carModelNum);

		System.out.println("Counting Cars using Parallel Stream");
		int carModelNumParallel = list.stream().parallel().map((Car car) -> car.getModel()).reduce(0,
				(result, carModel) -> ++result, (finalResult, intermediateResult) -> finalResult + intermediateResult);
		System.out.println("Car count: " + carModelNumParallel);
	}
}