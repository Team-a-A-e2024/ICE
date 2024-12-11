package util;

import Model.User;
import Persistens.UserRepo;

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


    public boolean signUp(String userName, String password) {

            ArrayList<User> users = UserRepo.loadUsers();

            assert users != null;
            if (users.isEmpty()) {
                User newUser = new User(userName, password);
                UserRepo.saveUser(newUser);
                System.out.println("Signup successful");
                return true;
            }
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
