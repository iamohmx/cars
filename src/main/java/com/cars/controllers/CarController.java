package com.cars.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale.Category;
import java.util.Scanner;
import java.util.Vector;
import com.cars.config.DBConnect;
import com.cars.models.Car;

public class CarController {
    private Vector<Car> Cars;
    private Scanner scanner;

    public CarController() {
        Cars = new Vector<Car>();
        scanner = new Scanner(System.in);
    }

    public void updateCar() {
        System.out.println("CarDB.updateCar()");
        try {
            System.out.print("Plate:");
            int plate = Integer.parseInt(scanner.nextLine());
            System.out.print("Model:");
            String model = scanner.nextLine();
            System.out.print("Make:");
            String make = scanner.nextLine();

            String updateSql = "UPDATE cars SET MODEL=?, MAKE=? WHERE PLATE=?";
            System.out.println("updateSql: " + updateSql);
            try 
			{
				DBConnect connDB = new DBConnect();
				Connection con = connDB.connect();
				PreparedStatement pstmt = con.prepareStatement(updateSql);
                pstmt.setString(1, model);
                pstmt.setString(2, make);
                pstmt.setInt(3, plate);
                pstmt.executeUpdate();
                System.out.println("Car updated successfully.");
            
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid plate.");
			}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteCar() {
        System.out.println("CarDB.deleteCar()");
        try {
            System.out.print("Plate:");
            int plate = Integer.parseInt(scanner.nextLine());

            String deleteSql = "DELETE FROM cars WHERE PLATE=?";
            System.out.println("deleteSql: " + deleteSql);

            try
			{
				DBConnect connDB = new DBConnect();
				Connection con = connDB.connect();
				PreparedStatement pstmt = con.prepareStatement(deleteSql);

                pstmt.setInt(1, plate);
                pstmt.executeUpdate();
                System.out.println("Deleted successfully.");
            } catch (NumberFormatException ex) {
				System.err.println("Error! Invalid plate.");
			}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchCar() {
        System.out.println("CarDB.search()");
        try {
            System.out.print("Plate:");
            int plate = Integer.parseInt(scanner.nextLine());

            String searchSql = "SELECT * FROM cars WHERE PLATE=?";
            System.out.println("selectSql: " + searchSql);

            try {
				DBConnect connDB = new DBConnect();
				Connection con = connDB.connect();
				PreparedStatement pstmt = con.prepareStatement(searchSql);

                pstmt.setInt(1, plate);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    plate = rs.getInt("PLATE");
                    String model = rs.getString("MODEL");
                    String make = rs.getString("MAKE");
                    Car car = new Car(plate, model, make);
                    System.out.println("Car: " + car);
                } else {
                    System.out.println("No car found with that plate.");
                }
            
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid plate.");
			}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addCars() {
        System.out.println("CarDB.addCars()");
        try {
            System.out.print("Plate:");
            int plate = Integer.parseInt(scanner.nextLine());
            System.out.print("Model:");
            String model = scanner.nextLine();
            System.out.print("Make:");
            String make = scanner.nextLine();
            Car car = new Car(plate, model, make);

            String insertSql = "INSERT INTO cars VALUES (?, ?, ?)";
            System.out.println("insertSql: " + insertSql);

            try {
				DBConnect conDb = new DBConnect();
				Connection conn = conDb.connect();
				PreparedStatement pstmt = conn.prepareStatement(insertSql);

                pstmt.setInt(1, car.plate);
                pstmt.setString(2, car.model);
                pstmt.setString(3, car.make);
                pstmt.executeUpdate();
                System.out.println("Car added successfully.");
        
        	} catch (NumberFormatException ex) {
            	System.err.println("Error! Invalid plate.");
			}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void viewCars() {
        System.out.println("CarDB.viewCars()");
        String SQL_SELECT = "SELECT * FROM cars;";
        Vector<Car> cars = new Vector();

    	DBConnect conDb = new DBConnect();
        Connection conn = conDb.connect();

        if (conn != null) {
            try {

                PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
					int id = rs.getInt("ID");
					int plate = rs.getInt("PLATE");
					String model = rs.getString("MODEL");
					String make = rs.getString("MAKE");
					Car car = new Car(id, plate, model, make);
					cars.add(car);
				}

                preparedStatement.close();
                conn.close();

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public void mainMenu() {
        boolean cont = true;
        while (cont) {
            System.out.println("=== Main Menu ===");
            System.out.println("1 Add Car.");
            System.out.println("2 Update Car.");
            System.out.println("3 Search Car.");
            System.out.println("4 Delete Car.");
            System.out.println("9 View Cars.");
            System.out.println("0 Exit");
            System.out.print("Your choice:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addCars();
                    break;
                case "2":
                    updateCar();
                    break;
                case "3":
                    searchCar();
                    break;
                case "4":
                    deleteCar();
                    break;
                case "9":
                    viewCars();
                    break;
                case "0":
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
