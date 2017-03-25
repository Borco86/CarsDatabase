package com.example.rent.carsdatabase;

public class Car {
    private String make;
    private String model;
    private int year;
    private String image;

    public Car(String make, String model, int year, String image) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.image = image;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }
}
