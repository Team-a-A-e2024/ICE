package Persistens;

import Model.Dish;
import Model.Product;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class DishRepo {

    static String loadDish = "SELECT * FROM DISHES";

    static ArrayList<Dish> dishes = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<Dish> loadDish() {

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
                for (String p : product.split(",")) {
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
        return dishes;
    }

    public static boolean saveDish(Dish dish) {
        String insertUserQuery = "INSERT INTO Dishes (name, dishweight, dishCalories, products) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            ArrayList<Dish> dishes = loadDish();
            if(dishes.isEmpty()) {
                System.out.println("Dish list is empty");

                PreparedStatement pstmt = con.prepareStatement(insertUserQuery);
                StringBuilder tempProducts = new StringBuilder(); //A string you can add to (Append)

                for(int i = 0; i < dish.getProducts().size(); i++) {
                    tempProducts.append(dish.getProducts().get(i).toString());
                    tempProducts.append(", ");

                }

                pstmt.setString(1, dish.getName());
                pstmt.setDouble(2, dish.getDishWeight());
                pstmt.setInt(3, dish.getDishCalories());
                pstmt.setString(4, tempProducts.toString());

                int affectedRows = pstmt.executeUpdate();

                pstmt.close();
                con.close();

                return affectedRows > 0;
            }
            else{
                for(Dish d : dishes) {
                    if(d.getName().equals(dish.getName())) {
                        System.out.println("Dish already exists");
                        con.close();
                    }
                    else{
                        PreparedStatement pstmt = con.prepareStatement(insertUserQuery);
                        StringBuilder tempProducts = new StringBuilder(); //A string you can add to (Append)

                        for(int i = 0; i < dish.getProducts().size(); i++) {
                            tempProducts.append(dish.getProducts().get(i).toString());
                            tempProducts.append(",");

                        }

                        pstmt.setString(1, dish.getName());
                        pstmt.setDouble(2, dish.getDishWeight());
                        pstmt.setInt(3, dish.getDishCalories());
                        pstmt.setString(4, tempProducts.toString());

                        int affectedRows = pstmt.executeUpdate();

                        pstmt.close();
                        con.close();

                        return affectedRows > 0;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
