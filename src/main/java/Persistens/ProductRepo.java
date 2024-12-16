package Persistens;

import Model.Product;
import org.w3c.dom.Text;
import util.TextUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ProductRepo {

    static String loadProduct = "SELECT * FROM Products";

    static ArrayList<Product> products = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<Product> loadProducts() {

        try (Connection con = DriverManager.getConnection(connectionString)) {
            TextUI.displayMsg("Connected to database");

            // The Statement is used to send SQL queries to the database. (SELECT, INSERT etc.)
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(loadProduct);

            while (rs.next()) {
                String name = rs.getString("name");
                String barcode = rs.getString("barcode");
                double weight = rs.getFloat("weight");
                int calories = rs.getInt("calorie");
                int carbs = rs.getInt("carb");
                int sugar = rs.getInt("sugar");
                int protein = rs.getInt("protein");
                int fat = rs.getInt("fat");

                Product product = new Product(name, barcode, weight, calories, carbs, sugar, protein, fat);
                products.add(product);
            }
            stmt.close();
            con.close();
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }

    public static boolean saveProduct(Product product) {
        String insertProductQuery = "INSERT INTO Products (name, barcode, weight, calorie, carb, sugar, protein, fat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(connectionString)) {
            PreparedStatement pstmt = con.prepareStatement(insertProductQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getBarcode());
            pstmt.setDouble(3, product.getWeight());
            pstmt.setInt(4, product.getCalorie());
            pstmt.setInt(5, product.getCarb());
            pstmt.setInt(6, product.getSugar());
            pstmt.setInt(7, product.getProtein());
            pstmt.setInt(8, product.getFat());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1)); // Sæt det genererede ID i produktet
                }
            }
            pstmt.close();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public static void createProductByUser() {

        boolean succes = false;

        while (!succes)
            try {
                String name = TextUI.promptText("Enter the name of the product:");
                String barcode = TextUI.promptText("Enter the barcode of the product:");
                double weight = TextUI.promptDouble("Enter the weight of the product (in grams):");
                int calorie = TextUI.promptInt("Enter the number of calories in the product:");
                int carb = TextUI.promptInt("Enter the carbohydrate content of the product (in grams):");
                int sugar = TextUI.promptInt("Enter the sugar content of the product (in grams):");
                int protein = TextUI.promptInt("Enter the protein content of the product (in grams):");
                int fat = TextUI.promptInt("Enter the fat content of the product (in grams):");

                Product product = new Product(name, barcode, weight, calorie, carb, sugar, protein, fat);
                saveProduct(product);
                TextUI.displayMsg("Product added successfully");
                succes = true;

            } catch (InputMismatchException iME) {
                TextUI.displayMsg("Invalid input! Please ensure you enter the correct type of value. Try again");
                // Clear the invalid input from the buffer
                TextUI.clearInputBuffer();
            }
    }
}