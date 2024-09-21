package com.cars.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    
    DBConfig dbConfig = new DBConfig(
        "jdbc:mysql://localhost:3306/cars",
        "root",
        ""
    );

    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        try {
            connection = DriverManager.getConnection(
                dbConfig.url,
                dbConfig.username,
                dbConfig.password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (connection == null) {
            System.out.println("Failed to connect to the database");
        }


        return connection;
    }
}
