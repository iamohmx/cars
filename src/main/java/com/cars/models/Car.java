package com.cars.models;

public class Car {
    public int id;
    public int plate;
    public String model;
    public String make;

    public Car( int plate, String model, String make) {
        this.plate = plate;
        this.model = model;
        this.make = make;
    }

    public Car(int id, int plate, String model, String make) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.make = make;
    }
    public String toString() {
        return "Car [id= "+ id +" plate=" + plate + ", model=" + model + ", make=" + make + " ]";
    }
}
