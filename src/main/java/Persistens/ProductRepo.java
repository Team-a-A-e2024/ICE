package Persistens;

import Model.Product;
import util.TextUI;

import java.sql.*;
import java.util.ArrayList;

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

}
