package Persistens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SeedDB {
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    static String loadFullUser = "SELECT userName, password FROM Users";

    public static void createDB() {

        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            try (Statement stmt = con.createStatement()) {
                // Create Users Table
                stmt.execute("""
                            CREATE TABLE IF NOT EXISTS Users (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                userName VARCHAR(50),
                                password VARCHAR(50),
                                allergens VARCHAR(50),
                                products VARCHAR(50),
                                dishes VARCHAR(50)
                            );
                        """);

                // Create Dishes Table
                stmt.execute("""
                            CREATE TABLE IF NOT EXISTS Dishes (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name VARCHAR(50),
                                dishWeight FLOAT(10),
                                dishCalories INT(10),
                                products VARCHAR(50)
                            );
                        """);

                // Create Products Table
                stmt.execute("""
                            CREATE TABLE IF NOT EXISTS Products (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name VARCHAR(50),
                                weight FLOAT(10),
                                calories INT(10)
                            );
                        """);

                System.out.println("Tables created successfully");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
