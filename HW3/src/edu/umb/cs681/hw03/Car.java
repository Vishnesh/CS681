package edu.umb.cs681.hw03;

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
		int y = 0;
		List<Car> list = new ArrayList<Car>();
		list.add(new Car("Porsche", "Cayenne", 19, 2003, 67500));
		list.add(new Car("BMW", "M5", 15, 1985, 103500));
		list.add(new Car("Audi", "A6", 23, 2004, 54900));

		int max_cost = list.stream().map((Car car) -> car.getPrice()).reduce(0,
				(result, price) -> result > price ? result : price);
		System.out.println("Price of Expensive car $ " + max_cost);

		int min_cost = list.stream().map((Car car) -> car.getPrice()).reduce(1000000000,
				(result, price) -> price > result ? result : price);
		System.out.println("Price of cheapest car $ " + min_cost);

		int count = list.stream().map(x -> y + 1).reduce(0, (a, b) -> a + b);
		System.out.println("Total number of cars in list : " + count);
	}
}