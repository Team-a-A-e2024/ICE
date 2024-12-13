package util;

import Model.User;
import Persistens.UserRepo;

import java.util.ArrayList;

public class Authorization {
    // function used to log in a user
    public boolean login(String userName, String password) {
        //gets all users in database
        ArrayList<User> users = UserRepo.loadUsers();
        //Login fail if there is no users in the database

        if (users == null || users.isEmpty()) {
            util.TextUI.displayMsg("Invalid username or password");
            return false;
        }
        // loops through all users and sees if input username and password is a match with preexisting users
        // will give corresponding message depending on what is not matching
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                if (u.getPassword().equals(password)) {
                    util.TextUI.displayMsg("Login successful");
                    return true;
                } else {

                    util.TextUI.displayMsg("Invalid username or password");
                    return false;
                }
            }
        }
        util.TextUI.displayMsg("Invalid username or password");
        return false;
    }

    // creates a user in DB with exception for if they share username
    public boolean signUp(String userName, String password) {

        // if the list of users is empty we can add a new user
        ArrayList<User> users = UserRepo.loadUsers();

        if (users == null) {
            users = new ArrayList<>();
        }
        // checks if there is already a user with the same username
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                util.TextUI.displayMsg("Username already exists");
                return false;
            }
        }
        // creates a new user in the DB
        User newUser = new User(userName, password);
        UserRepo.saveUser(newUser);
        util.TextUI.displayMsg("Signup successful");
        return true;
    }
}