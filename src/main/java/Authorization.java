import Model.User;
import Persistens.UserRepo;

import java.sql.*;
import java.util.ArrayList;

public class Authorization {

    public boolean login(String userName, String password) {

        ArrayList<User> users = UserRepo.loadUsers();

        if (users.isEmpty()) {
            System.out.println("Login failed: No users found in the database.");
            return false;
        }

        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                if (u.getPassword().equals(password)) {
                    System.out.println("Login successful");
                    return true;
                } else {
                    System.out.println("password does not match");
                    return false;
                }
            } else {
                System.out.println("Username does not match");
                return false;
            }
        }
        return false;

    }


//    public boolean signUp(String userName, String password) {
//        ArrayList<User> users = new ArrayList<>();
//
//        String connectionString = "jdbc:sqlite:identifier.sqlite";
//        try(Connection con = DriverManager.getConnection(connectionString)){
//
//            System.out.println("Connected to database");
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(loadUserName);
//
//            while (rs.next()) {
//                String name = rs.getString("userName");
//
//
//                User user = new User(userName);
//                users.add(user);
//            }
//
//            for(User u : users){
//                if(u.getUserName().equals(userName)){
//                    System.out.println("Username already exists");
//                    return false;
//                }
//                else{
//                    String save = "INSERT INTO Users (userName, password) VALUES (?, ?)";
//                    PreparedStatement ps = con.prepareStatement(save);
//                    ps.setString(1, userName);
//                    ps.setString(2, password);
//
//                    int rowInserted = ps.executeUpdate();
//
//                    //Checks if data is inserted in database
//                    if(rowInserted > 0){
//                        System.out.println("Data inserted successfully");
//                    }
//
//                    ps.close();
//                    con.close();
//                    stmt.close();
//
//                    System.out.println("Signup successful");
//                    return true;
//                }
//            }
//
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }
}
