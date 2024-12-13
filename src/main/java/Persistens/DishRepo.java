package Persistens;
import Model.Dish;
import Model.Product;
import Model.User;
import enums.DishCategory;
import util.TextUI;

import java.sql.*;
import java.util.ArrayList;

import static Persistens.DishProductRepo.addProductToDish;
import static Persistens.ProductRepo.saveProduct;


public class DishRepo {

    static String loadDish = "SELECT * FROM DISHES";

    static ArrayList<Dish> dishes = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<Dish> loadDish() {
        ArrayList<Dish> dishes = new ArrayList<>();

        String loadDishQuery = "SELECT * FROM Dishes";

        try (Connection con = DriverManager.getConnection(connectionString)) {
            TextUI.displayMsg("Connected to database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(loadDishQuery);

            while (rs.next()) {
                int dishId = rs.getInt("id");
                String name = rs.getString("name");
                double dishWeight = rs.getDouble("dishWeight");
                int dishCalories = rs.getInt("dishCalorie");
                String dishCategoryString = rs.getString("dishCategory");

                //Convert string to enum
                DishCategory dishCategory = DishCategory.valueOf(dishCategoryString);

                // Hent produkter for denne dish
                ArrayList<Product> products = DishProductRepo.getProductsForDish(dishId);

                // Opret Dish-objekt
                Dish dish = new Dish(name, dishWeight, dishCalories, products, dishCategory);
                dishes.add(dish);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishes;
    }


    public static boolean saveDish(Dish dish) {
        String insertDishQuery = "INSERT INTO Dishes (name, dishWeight, dishCalorie, dishCategory) VALUES (?, ?, ?, ?)";
        String insertDishProductQuery = "INSERT INTO DishProducts (dishId, productId) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(connectionString)) {
            TextUI.displayMsg("Connected to database");

            // Check if the dish already exists
            ArrayList<Dish> dishes = loadDish();
            for (Dish d : dishes) {
                if (d.getName().equalsIgnoreCase(dish.getName())) {
                    TextUI.displayMsg("Dish already exists");
                    return false;
                }
            }

            // Insert the dish
            PreparedStatement dishStmt = con.prepareStatement(insertDishQuery, Statement.RETURN_GENERATED_KEYS);
            dishStmt.setString(1, dish.getName());
            dishStmt.setDouble(2, dish.getDishWeight());
            dishStmt.setInt(3, dish.getDishCalories());
            dishStmt.setString(4, dish.getDishCategory().name());

            int affectedRows = dishStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save the dish.");
            }

            // Get the dish ID
            ResultSet generatedKeys = dishStmt.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Failed to retrieve dish ID.");
            }
            int dishId = generatedKeys.getInt(1);
            dishStmt.close();

            // Save products and link them to the dish
            for (Product product : dish.getProducts()) {
                // Save the product if it doesn't exist
                saveProduct(product);

                // Link product to the dish
                addProductToDish(dishId, product.getId());
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void addDishWithProducts(Dish dish, ArrayList<Integer> productIds) {
        String insertDish = "INSERT INTO Dishes (name, dishWeight, dishCalorie) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(connectionString)) {
            // Insert the dish
            PreparedStatement pstmt = con.prepareStatement(insertDish, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, dish.getName());
            pstmt.setDouble(2, dish.getDishWeight());
            pstmt.setInt(3, dish.getDishCalories());
            pstmt.executeUpdate();

            // Get the generated dish ID
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int dishId = rs.getInt(1);

                // Add products to the dish
                for (int productId : productIds) {
                    addProductToDish(dishId, productId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
