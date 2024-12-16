package Persistens;

import Models.User;
import util.TextUI;

import java.sql.*;
import java.util.ArrayList;

public class UserRepo {
    static String loadFullUser = "SELECT userName, password FROM Users";
    static String loadUserName = "SELECT userName FROM Users";

    static ArrayList<User> users = new ArrayList<>();

    //Test if we are connected to database
    static String connectionString = "jdbc:sqlite:" + System.getProperty("user.dir") + "/identifier.sqlite";

    public static ArrayList<User> loadUsers(){

        try (Connection con = DriverManager.getConnection(connectionString)) {

            // The Statement is used to send SQL queries to the database. (SELECT, INSERT etc.)
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(loadFullUser);

            while (rs.next()) {
                String name = rs.getString("userName");
                String pass = rs.getString("password");

                User user = new User(name, pass);
                users.add(user);
            }
            stmt.close();
            con.close();
            return users;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean saveUser(User user) {
        String insertUserQuery = "INSERT INTO Users (userName, password) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(connectionString)) {

            PreparedStatement pstmt = con.prepareStatement(insertUserQuery);

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());

            int affectedRows = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
