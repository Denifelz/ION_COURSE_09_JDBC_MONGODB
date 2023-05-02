package com.jdbc.mongodb.tutorial;

import mongodb.jdbc.MongoDriver;

import java.sql.SQLException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new MongoDriver());
            System.out.println("INFO: MongoDB JDBC Driver loaded successfully.");
            System.out.println();

            try (Connection dbConnection = DriverManager.getConnection("jdbc:mongo://localhost:27017/JDBC_Tutorial", "denis", "denis")) {
                System.out.println("INFO: Connection with the database established successfully.");
                System.out.println();

                try (Statement dbStatement = dbConnection.createStatement()) {
                    System.out.println("INFO: Statement created successfully.");
                    System.out.println();

                    try {
                        dbStatement.executeUpdate("delete from cities;");

                        System.out.println("INFO: Delete executed successfully.");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("ERROR: Unable to delete records!");
                        throw new RuntimeException(e);
                    }

                    try {
                        dbStatement.executeUpdate("insert into cities (name, population, budget) values ('Chisinau', 1000000, 999999.99);");
                        dbStatement.executeUpdate("insert into cities (name, population, budget) values ('Balti', 200000, 100000);");
                        dbStatement.executeUpdate("insert into cities (name, population, budget) values ('Cahul', 100000, 90000);");

                        System.out.println("INFO: Insert executed successfully.");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("ERROR: Unable to insert records!");
                        throw new RuntimeException(e);
                    }

                    try (ResultSet selectAllFromCities = dbStatement.executeQuery("select * from cities;")) {
                        System.out.println("INFO: Query created successfully.");
                        System.out.println();

                        try {
                            System.out.println("City|Population|Budget");
                            System.out.println("======================");
                            while (selectAllFromCities.next()) {
                                String name = selectAllFromCities.getString("name");
                                int population = selectAllFromCities.getInt("population");
                                double budget = selectAllFromCities.getDouble("budget");

                                System.out.println(name + "|" + population + "|" + budget);
                            }
                            System.out.println();

                            System.out.println("INFO: Fetch executed successfully.");
                            System.out.println();
                        } catch (Exception e) {
                            System.out.println("ERROR: Unable to fetch records!");
                            throw new RuntimeException(e);
                        }
                    } catch (SQLException e) {
                        System.out.println("ERROR: Unable to create query!");
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: Unable to create statement!");
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                System.out.println("ERROR: Unable to establish a connection with the database!");
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to load MongoDB JDBC Driver!");
            throw new RuntimeException(e);
        }
    }
}
