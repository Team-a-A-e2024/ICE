package Persistens;
import Model.Product;
import Model.User;
import util.TextUI;
import java.sql.*;
import java.util.ArrayList;

public class DishProductRepo {
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static void addProductToDish(int dishId, int productId) {
        String insertDishProductQuery = "INSERT INTO DishProducts (dishId, productId) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(connectionString)) {

            PreparedStatement pstmt = con.prepareStatement(insertDishProductQuery);
            pstmt.setInt(1, dishId);
            pstmt.setInt(2, productId);

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




