package Util;

import Model.User;
import util.TextUI;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import static java.lang.System.exit;

public class Menu {

    public static ArrayList<User> loginMenuLogic() {
        //Asks the user if they want to log in or sign-up
        List<String> options = new ArrayList();
        options.add("Log in");
        options.add("Sign up");
        options = TextUI.promptChoice(options, 1, "There's no user logged in");

        ArrayList<User> currentUser = null;

        //Depending on what they want to do it runs different functions for the login and sign-up function
        Authorization auth = new Authorization();
        switch (options.get(0)) {
            case "Log in":
                String username = TextUI.promptText("Please enter your username: ");
                String password = TextUI.promptText("Please enter your password: ");
                auth.login(username, password);
                break;
            case "Sign up":
                username = TextUI.promptText("Please create a new username: ");
                password = TextUI.promptText("Please create a new password: ");
                auth.signUp(username, password);
                break;
        }

        //If there's no user, it runs itself
        if (currentUser == null) {
            return loginMenuLogic();
        }
        return currentUser;
    }


    public static void displayMenu() {
        List<String> options = new ArrayList();
        options.add("Search products");
        options.add("See saved products");
        options.add("See saved recipes");
        options.add("Scan barcode");
        options.add("Exit");
        options = TextUI.promptChoice(options, 1, "What would you like to do?");
        Set<Menu> result = new HashSet<>();
        switch (options.get(0)) {
            case "Scan barcode":
                //result = searchMedia();
                //selectTitle(result);
                break;
            case "Search products":
                //result = searchCategory();
                //selectTitle(result);
                break;
            case "See saved products":
                //missing function. in user?
                break;
            case "See saved recipes":
                //missing function. in user?
                break;
            case "Exit":
                //saveWatchedAndSavedMedia();
                exit(0);
                break;
        }
        displayMenu();
    }
}