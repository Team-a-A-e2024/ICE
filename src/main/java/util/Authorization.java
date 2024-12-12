import Model.User;
import Persistens.UserRepo;
import util.TextUI;

import java.util.ArrayList;

public class Authorization {

    public boolean login(String userName, String password) {

        ArrayList<User> users = UserRepo.loadUsers();

        if (users == null || users.isEmpty()) {
            TextUI.displayMsg("Invalid username or password");
            return false;
        }

        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                if (u.getPassword().equals(password)) {
                    TextUI.displayMsg("Login successful");
                    return true;
                } else {

                    TextUI.displayMsg("Invalid username or password");
                    return false;
                }
            }
        }
        TextUI.displayMsg("Invalid username or password");
        return false;
    }

    public boolean signUp(String userName, String password) {

        ArrayList<User> users = UserRepo.loadUsers();

        if (users == null) {
            users = new ArrayList<>();
        }

        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                TextUI.displayMsg("Username already exists");
                return false;
            }
        }

        User newUser = new User(userName, password);
        UserRepo.saveUser(newUser);
        TextUI.displayMsg("Signup successful");
        return true;
    }
}
