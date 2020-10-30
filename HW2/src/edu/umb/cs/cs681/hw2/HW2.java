package edu.umb.cs.cs681.hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HW2 {

    private static List<Car> cars = new ArrayList<Car>();
    
    static Optional<Float> min() {
        return cars.stream()
            .map((car) -> car.getPrice())
            .reduce(Math::min);
    }

    static Optional<Float> max() {
        return cars.stream()
            .map((car) -> car.getPrice())
            .reduce(Math::max);
    }
    static Integer count() {
        return cars.stream()
            .map((car) -> 1)
            .reduce(0, (result, cnt) -> result + 1);
    }

    public static void main(String args[]) {
        cars.add(new Car("Hundai", "Rxp", 2017, 5000, 20));
        cars.add(new Car("Bmw", "Xls", 2015, 250, 12));
        cars.add(new Car("audi", "cdi",2000, 1400, 15));
        cars.add(new Car("Abc", "xyz", 2017, 5300, 10));
        System.out.println(min().get());
        System.out.println(max().get());
        System.out.println(count());
    }
}
