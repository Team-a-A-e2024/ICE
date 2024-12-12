package util;

import Model.User;
import Persistens.UserRepo;

import java.util.ArrayList;

public class Authorization {

    //function used to long in a user
    public boolean login(String userName, String password) {

        //gets all users in database
        ArrayList<User> users = UserRepo.loadUsers();
        //login will fail if there is no users in the database
        if (users.isEmpty()) {
            System.out.println("Login failed: No users found in the database.");
            return false;
        }

        //loops through all users and sees if input username and password is a match with a preexisting match
        //will give corresponding message depending on what is not matching
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

    //creates a user in DB with exception for if they share username
    public boolean signUp(String userName, String password) {

            ArrayList<User> users = UserRepo.loadUsers();

            //if the list of users is empty we can just add a new user
            assert users != null;
            if (users.isEmpty()) {
                User newUser = new User(userName, password);
                UserRepo.saveUser(newUser);
                System.out.println("Signup successful");
                return true;
            }
            //else we check every user if they share a name and if they do the signup fails
            else{
                for(User u : users){
                    if(u.getUserName().equals(userName)){
                        System.out.println("Username already exists");
                        return false;
                    }
                    else{
                        User newUser = new User(userName, password);
                        UserRepo.saveUser(newUser);
                        System.out.println("Signup successful");
                        return true;
                    }
                }
            }
            return false;
    }
}
