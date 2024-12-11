import Model.User;
import Persistens.UserRepo;
import util.TextUI;

import java.sql.*;
import java.util.ArrayList;

public class Authorization {

    public boolean login(String userName, String password) {

        ArrayList<User> users = UserRepo.loadUsers();

        if (users.isEmpty()) {
            TextUI.displayMsg("Login failed: No users found in the database.");
            return false;
        }

        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                if (u.getPassword().equals(password)) {
                    TextUI.displayMsg("Login successful");
                    return true;
                } else {
                    TextUI.displayMsg("password does not match");
                    return false;
                }
            } else {
                TextUI.displayMsg("Username does not match");
                return false;
            }
        }
        return false;

    }


    public boolean signUp(String userName, String password) {

            ArrayList<User> users = UserRepo.loadUsers();

            assert users != null;
            if (users.isEmpty()) {
                User newUser = new User(userName, password);
                UserRepo.saveUser(newUser);
                TextUI.displayMsg("Signup successful");
                return true;
            }
            else{
                for(User u : users){
                    if(u.getUserName().equals(userName)){
                        TextUI.displayMsg("Username already exists");
                        return false;
                    }
                    else{
                        User newUser = new User(userName, password);

                        UserRepo.saveUser(newUser);
                        TextUI.displayMsg("Signup successful");
                        return true;
                    }
                }
            }
            return false;
    }
}
