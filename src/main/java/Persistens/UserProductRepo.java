package Persistens;

import Model.UserProduct;

import java.sql.*;
import java.util.ArrayList;
//A class that should have logic for only adding product to the specific user
public class UserProductRepo {

    static String loadUserProductsQuery = "SELECT * FROM UserProducts";
    static String insertUserProductQuery = "INSERT INTO UserProducts (userproduct, userId) VALUES (?, ?)";

    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<UserProduct> loadUserProducts() {
        ArrayList<UserProduct> userProducts = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(loadUserProductsQuery);

            while (rs.next()) {
                int id = rs.getInt("id");
                String userProduct = rs.getString("userproduct");
                int userId = rs.getInt("userId");

                UserProduct userProd = new UserProduct(id, userProduct, userId);
                userProducts.add(userProd);
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProducts;
    }

    public static boolean saveUserProduct(UserProduct userProduct) {
        try (Connection con = DriverManager.getConnection(connectionString)) {
            System.out.println("Connected to database");

            ArrayList<UserProduct> userProducts = loadUserProducts();
            for (UserProduct up : userProducts) {
                if (up.getUserProduct().equals(userProduct.getUserProduct()) &&
                        up.getUserId() == userProduct.getUserId()) {
                    System.out.println("UserProduct already exists");
                    return false;
                }
            }

            PreparedStatement pstmt = con.prepareStatement(insertUserProductQuery);
            pstmt.setString(1, userProduct.getUserProduct());
            pstmt.setInt(2, userProduct.getUserId());

            int affectedRows = pstmt.executeUpdate();
            pstmt.close();
            con.close();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO MAKE A LoadUserProduct
}
