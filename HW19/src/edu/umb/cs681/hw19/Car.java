package edu.umb.cs681.hw19;

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

		Integer minPrice = list.stream().parallel().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice < result)
				return carPrice;
			else
				return result;
		}, (finalResult, interMediateResult) -> {
			System.out.println(Thread.currentThread().getName() + " : finalResult = " + finalResult
					+ "; interMediateResult = " + interMediateResult);
			return (finalResult < interMediateResult) ? finalResult : interMediateResult;
		});
		System.out.println("The Min price: " + minPrice);
		System.out.println("Max Price Method result --------: ");

		Integer maxPrice = list.stream().map((Car car) -> car.getPrice()).parallel().reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice >= result)
				return carPrice;
			else
				return result;
		}, (finalResult, interMediateResult) -> {
			System.out.println(Thread.currentThread().getName() + " : finalResult = " + finalResult
					+ "; interMediateResult = " + interMediateResult);
			return (finalResult > interMediateResult) ? finalResult : interMediateResult;
		});
		System.out.println("The Max price: " + maxPrice);
		System.out.println("Count Method result --------: ");

		Integer count = list.stream().parallel().map((Car car) -> car.getPrice()).reduce(0, (result, car) -> {
			if (car != null)
				return ++result;
			return result;
		}, (finalResult, intermediateResult) -> {
			System.out.println(Thread.currentThread().getName() + " : finalResult = " + finalResult
					+ "; interMediateResult = " + intermediateResult);
			return finalResult + intermediateResult;
		});
		System.out.println("The count Method: " + count);
	}
}