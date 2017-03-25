package com.example.rent.carsdatabase;

public class CarBuilder {
    private String make;
    private String model;
    private int year;
    private String image;

    public CarBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public CarBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public Car createCar() {
        return new Car(make, model, year, image);
    }
}