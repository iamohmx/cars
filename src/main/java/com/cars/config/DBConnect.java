package com.cars.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    
    public DBConfig dbConfig;

    public DBConnect() {
        this.dbConfig = new DBConfig(
            "jdbc:mariadb://localhost:3306/garage",
            "root",
            ""
        );
    }

    public Connection connect() {
        Connection connection = null;
        try {
            // Load MySQL JDBC driver
            Class.forName("org.mariadb.jdbc.Driver"); // Use MySQL driver
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
            return null;
        }
        
        try {
            connection = DriverManager.getConnection(
                dbConfig.url,
                dbConfig.username,
                dbConfig.password
            );
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
        
        if (connection == null) {
            System.out.println("Failed to connect to the database");
        }

        return connection;
    }
}
