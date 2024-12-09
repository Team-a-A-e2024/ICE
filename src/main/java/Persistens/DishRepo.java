package Persistens;

import Model.Dish;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;

public class DishRepo {

    static String loadDish = "SELECT * FROM DISHES";

    static ArrayList<Dish> dishes = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<Dish> loadProducts() {

        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            // The Statement is used to send SQL queries to the database. (SELECT, INSERT etc.)
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(loadDish);

            while (rs.next()) {
                String name = rs.getString("name");
                double dishWeight = rs.getFloat("dishWeight");
                int dishCalories = rs.getInt("DishCalories");
                String product = rs.getString("products"); //

                ArrayList<Product> products = new ArrayList<>();
                for (String p : product.split("\s+")) {
                    products.add(new Product(p.trim())); // Create a new Product object
                }

                Dish dish = new Dish(name, dishWeight, dishCalories, products);
                dishes.add(dish);
            }
            stmt.close();
            con.close();
            return dishes;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
