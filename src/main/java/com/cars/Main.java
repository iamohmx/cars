package com.cars;

import com.cars.controllers.CarController;

public class Main {
    public static void main(String[] args) {
        // https://khamreang.msu.ac.th/~chatra/2565-1-prog-2/

        // Create a new Car object
        CarController carController = new CarController();

        carController.mainMenu();
    }
}