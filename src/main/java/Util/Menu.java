package Util;

import Model.User;
import barscanner.Barscanner;
import util.TextUI;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static barscanner.Barscanner.readCodeFromPath;
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
        options.add("Saved products");
        options.add("Saved recipes");
        options.add("Scan barcode");
        options.add("Exit");
        options = TextUI.promptChoice(options, 1, "What would you like to do?");
        Set<Menu> result = new HashSet<>();
        switch (options.get(0)) {
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
            case "Scan barcode":
                barcodeMenu();
                break;
            case "Exit":
                //saveWatchedAndSavedMedia();
                exit(0);
                break;
        }
        displayMenu();
    }

    public static void barcodeMenu() {
        List<String> options = new ArrayList();
        options.add("Scan barcode");
        options.add("Enter barcode manually");
        options.add("Return to main menu");
        TextUI.promptChoice(options, 1, "Please choose an option: ");
        switch (options.get(0)) {
            case "Scan barcode":
                String filePath = TextUI.promptText("Please enter the barcode you wish to scan: ");
                readCodeFromPath(filePath);
                //Todo: Parse String to get a product
                break;
            case "Enter barcode manually":
                String typeBarcode = TextUI.promptText("Please type the barcode number you wish to scan: ");
                //Todo: Parse String to get a product
                break;
            case "Return to main menu":
                return;
            default:
                TextUI.displayMsg("Please choose a valid option");
                barcodeMenu();
                break;
        }
    }
    public static void searchProductMenu() {
        //Todo: enter searchProduct function when it is ready by Fred.
    }
    public static void searchDishMenu() {
        //Todo: Enter searchDish function when it is ready.
    }
}