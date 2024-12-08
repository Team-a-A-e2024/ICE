package Persistens;
import Model.Product;
import Model.User;
import java.sql.*;
import java.util.ArrayList;

public class PoductRepo {



    static String loadProduct = "SELECT * FROM Products";

    static ArrayList<Product> products = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:C:\\Users\\Alissa\\IdeaProjects\\ICE\\identifier.sqlite";

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
        return null;

    }

    //TODO Make a save method
}
