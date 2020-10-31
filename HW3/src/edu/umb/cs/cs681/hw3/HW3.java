package edu.umb.cs.cs681.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HW3 {

    private static List<Car> cars = new ArrayList<Car>();

    public static List<Car> sortBy(Comparator<Car> comparator) {
        return cars.stream()
            .sorted(comparator)
            .collect(Collectors.toList());
    }
    
    public static void main(String args[]) {
        cars.add(new Car("Hundai", "Rxp", 2017, 5000, 20));
        cars.add(new Car("Bmw", "Xls", 2015, 250, 12));
        cars.add(new Car("audi", "cdi",2000, 1400, 15));
        cars.add(new Car("Abc", "xyz", 2017, 5300, 10));
        int count = cars.size() + 1;
        for (Car c: cars) {
            c.setDominationCount(count--);
        }

        System.out.print("Price: ");
        System.out.println(sortBy(Comparator.comparing(Car::getPrice)));
        System.out.print("Year: ");
        System.out.println(sortBy(Comparator.comparing(Car::getYear)));
        System.out.print("Mileage: ");
        System.out.println(sortBy(Comparator.comparing(Car::getMileage)));
        System.out.print("Dominant: ");
        System.out.println(sortBy(Comparator.comparing(Car::getDominationCount)));
    }
}
