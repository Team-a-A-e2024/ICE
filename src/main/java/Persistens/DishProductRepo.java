package Persistens;
import Models.Product;
import java.sql.*;
import java.util.ArrayList;

// Adds a product to a dish by inserting their IDs into the DishProducts table.
public class DishProductRepo {
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static void addProductToDish(int dishId, int productId) {
        // SQL query to insert a dish-product relationship into the DishProducts table
        String insertDishProductQuery = "INSERT INTO DishProducts (dishId, productId) VALUES (?, ?)";

        //Connecting to DB
        try (Connection con = DriverManager.getConnection(connectionString)) {

            //Setting query parameters for dishId and productId
            PreparedStatement pstmt = con.prepareStatement(insertDishProductQuery);
            pstmt.setInt(1, dishId);
            pstmt.setInt(2, productId);

            // Executing the insert operation
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getProductsForDish(int dishId) {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT Products.* FROM DishProducts INNER JOIN Products ON DishProducts.productId = Products.id WHERE DishProducts.dishId = ?";

        try (Connection con = DriverManager.getConnection(connectionString)) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, dishId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getString("barcode"),
                        rs.getDouble("weight"),
                        rs.getInt("calorie"),
                        rs.getInt("carb"),
                        rs.getInt("sugar"),
                        rs.getInt("protein"),
                        rs.getInt("fat")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}




