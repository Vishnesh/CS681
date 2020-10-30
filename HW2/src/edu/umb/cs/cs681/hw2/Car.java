package edu.umb.cs.cs681.hw2;

public class Car {
    
    public Car(String model, String make, int year, int price, int mileage) {
        super();
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
    }
    
    private String make, model;
    private int mileage, year;
    private float price;
    private int count;
    
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
    public void setDominationCount(int count) {
        this.count = count;
    }
    
    public int getDominationCount() {
        return count;
    }
}
