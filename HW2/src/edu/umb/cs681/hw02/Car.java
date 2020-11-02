package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {

	private String model, make;
	private int mileage, year;
	private float price;
	private int dominationCount;

	public Car(String make, String model, int mileage, int year, float price) {
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

	public float getPrice() {
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
		List<Car> carList = new ArrayList<Car>();
		carList.add(new Car("Porsche", "Cayenne", 19, 2003, 67500));
		carList.add(new Car("BMW", "M5", 15, 1985, 103500));
		carList.add(new Car("Audi", "A6", 23, 2004, 54900));

		System.out.println("Sorted by Year:");
		List<Car> sortedByYear = carList.stream().sorted(Comparator.comparingInt(Car::getYear))
				.collect(Collectors.toList());
		sortedByYear.forEach(System.out::println);

		System.out.println("");
		System.out.println("Sorted by Mileage:");
		List<Car> sortedByMileage = carList.stream().sorted(Comparator.comparingInt(Car::getMileage))
				.collect(Collectors.toList());
		sortedByMileage.forEach(System.out::println);

		System.out.println("");
		System.out.println("Sorted by Price:");
		List<Car> sortedByPrice = carList.stream().sorted(Comparator.comparingDouble(Car::getPrice))
				.collect(Collectors.toList());
		sortedByPrice.forEach(System.out::println);

		System.out.println("");
		System.out.println("Sorted by Domination Count:");
		List<Car> sortedByDomCount = carList.stream().sorted(Comparator.comparingInt(Car::getDominationCount))
				.collect(Collectors.toList());
		sortedByDomCount.forEach(System.out::println);
	}
}
