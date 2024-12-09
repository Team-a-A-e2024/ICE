package Persistens;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepo {



    static String loadProduct = "SELECT * FROM Products";

    static ArrayList<Product> products = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<Product> loadProducts() {

        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            // The Statement is used to send SQL queries to the database. (SELECT, INSERT etc.)
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(loadProduct);

            while (rs.next()) {
                String name = rs.getString("name");
                double weight = rs.getFloat("weight");
                int calories = rs.getInt("calories");

                Product product = new Product(name, weight, calories);
                products.add(product);
            }
            stmt.close();
            con.close();
            return products;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }

    public static boolean saveProduct(Product product) {
        String insertUserQuery = "INSERT INTO Products (name, weight, calories) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            ArrayList<Product> products = loadProducts();
            if(products.isEmpty()) {
                System.out.println("Product list is empty");
                PreparedStatement pstmt = con.prepareStatement(insertUserQuery);

                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getWeight());
                pstmt.setInt(3, product.getCalories());
                int affectedRows = pstmt.executeUpdate();

                pstmt.close();
                con.close();
                return affectedRows > 0;
            }
            else{
                for(Product p : products) {
                    if(p.getName().equals(product.getName())) {
                        System.out.println("Product already exists");
                        con.close();
                    }
                    else {
                        PreparedStatement pstmt = con.prepareStatement(insertUserQuery);

                        pstmt.setString(1, product.getName());
                        pstmt.setDouble(2, product.getWeight());
                        pstmt.setInt(3, product.getCalories());
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
