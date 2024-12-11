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
                                userId INTEGER PRIMARY KEY AUTOINCREMENT,
                                userName VARCHAR(50),
                                password VARCHAR(50),
                                allergen VARCHAR(50),
                                product VARCHAR(50),
                                dish VARCHAR(50)
                            );
                        """);

                // Create Dishes Table
                stmt.execute("""
                            CREATE TABLE IF NOT EXISTS Dishes (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name VARCHAR(50),
                                dishWeight FLOAT(10),
                                dishCalorie INT(10)
                            );
                        """);

                // Create Products Table
                stmt.execute("""
                            CREATE TABLE IF NOT EXISTS Products (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name VARCHAR(50),
                                barcode VARCHAR(50),
                                weight FLOAT(10),
                                calorie INT(10),
                                carb INT(10),
                                sugar INT(10),
                                protein INT(10),
                                fat INT(10)
                            );
                        """);

                //Junction Table to represent many-to-many relationship
                stmt.execute("""
                         CREATE TABLE IF NOT EXISTS DishProducts(
                                dishId INTEGER,
                                productId INTEGER,
                                PRIMARY KEY(dishId, productId),
                                FOREIGN KEY(dishId)REFERENCES Dishes(id)ON DELETE CASCADE,                    
                                FOREIGN KEY(productId)REFERENCES Products(id)ON DELETE CASCADE
                        );
                        """);
                System.out.println("Tables created successfully");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
