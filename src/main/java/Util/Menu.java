package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Menu {

    public static User loginMenuLogic(){
        //Asks the user if they want to log in or sign-up
        List<String> options = new ArrayList();
        options.add("Log in");
        options.add("Sign up");
        options = TextUI.promptChoice(options,1,"There's no user logged in");

        User currentUser = null;

        //Depending on what they want to do it runs different functions for the login and sign-up function
        switch (options.get(0)) {
            case "Log in":
                currentUser = login();
                break;
            case "Sign up":
                signUp();
                break;
        }

        //If there's no user, it runs itself
        if (currentUser == null){
            return loginMenuLogic();
        }
        return currentUser;
    }

    //The function for logging a user in
    public static User login() {
        {
            //gets the credentials for all users

            //SKAL FIXES: Map<String, String> users =  FileIO.readUserCredentials("data/credentials");

            //Asks the user for what their username and password is
            String username =  TextUI.promptText("Please enter your username: ");
            String password =  TextUI.promptText("Please enter your password: ");

            //Checks if the username is a valid key in Map
            //Then checks if that username key has the same password as entered
            if (users.containsKey(username)) {
                if (users.get(username).equals(password)) {
                    TextUI.displayMsg("You have successfully logged in. Welcome " + username + " to NotFlix");
                    return new User(username, password);
                }
            }
            //Error message for if user doesn't exist
            TextUI.displayMsg("Username or password is incorrect, please try again or Sign up");
            //Goes back to the previous method
            return loginMenuLogic();
        }
    }

    public static void signUp(){
        //Gets the credentials for all users
        // SKAL FIXES: Map<String, String> users =  FileIO.readUserCredentials("data/credentials");
        //Asks the user for what their username and password is
        String username = TextUI.promptText("Please create a new username: ");
        String password = TextUI.promptText("Please create a new password: ");
        //Invalidation checks
        //Checks if there is a space at the beginning or end of the username or password

        if (username.trim() != username || password.trim() != password) {
            TextUI.displayMsg("Username and password is incorrect \n username and password can't contain spaces");
            signUp();
            return;
        }
        //Checks if the username or password is empty
        else if (username == null || password == null || username.equals("") || password.equals("")) {
            TextUI.displayMsg("Username or password is empty, please try again");
            signUp();
            return;
        }
        //Checks if the username or password is less than 4 characters long
        else if (username.length() < 4 || password.length() < 4) {
            TextUI.displayMsg("Username and password must contian at least 4 characters, please try again");
            signUp();
            return;
        }
        //Checks if there is already a existing user with the same username
        if (users.containsKey(username)) {
            TextUI.displayMsg("Username already exists, please try again or please login");
            return;
        }
        //Saves the user which has passed the checks

        // SKAL FIXES: FileIO.saveCredentials(username, password, "data/credentials");

        TextUI.displayMsg("User created successfully. Welcome to NotFlix");
    }
}