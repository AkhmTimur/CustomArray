package com.example.customarray;

public class Car {
    private int wheels;
    private int year;
    private String bodyStyle;

    public Car(int wheels, int year, String bodyType) {
        this.wheels = wheels;
        this.year = year;
        this.bodyStyle = bodyType;
    }

    public int getWheels() {
        return wheels;
    }

    public int getYear() {
        return year;
    }

    public String getBodyType() {
        return bodyStyle;
    }
}

