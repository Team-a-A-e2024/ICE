package util;

import Model.Product;
import Model.User;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static Persistens.ProductRepo.saveProduct;
import static barscanner.Barscanner.readCodeFromPath;
import static java.lang.System.exit;
import static util.AppService.searchProducts;
import static util.ProductDetailsService.getProductByCode;

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
        options.add("View saved dishes");
        options.add("Scan barcode");
        options.add("Exit");
        options = TextUI.promptChoice(options, 1, "What would you like to do?");
        Set<Menu> result = new HashSet<>();
        switch (options.get(0)) {
            case "Search products":
                searchProductMenu();
                break;
            case "View saved dishes":
                //missing function. in user?
                break;
            case "Scan barcode":
                barcodeMenu();
                break;
            case "Exit":
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
        options = TextUI.promptChoice(options, 1, "Please choose an option: ");
        Product product = null;
        switch (options.get(0)) {
            case "Scan barcode":
                String filePath = TextUI.promptText("Please enter the barcode you wish to scan: ");
                product = getProductByCode(readCodeFromPath(filePath));
                getProductByCode(readCodeFromPath(filePath));
                productMenu(product);
                break;
            case "Enter barcode manually":
                String typeBarcode = TextUI.promptText("Please type the barcode number you wish to scan: ");
                product = getProductByCode(typeBarcode);
                productMenu(product);
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
        searchProducts();
        ArrayList<Product> searchProducts;
        searchProducts = searchProducts();
        for(Product product : searchProducts) {
            TextUI.displayMsg("You have found: " + product.getName());
        }
    }

    public static void seeDishMenu() {
        //Todo: Enter searchDish function when it is ready.
    }

    public static void productMenu(Product product) {
        TextUI.displayMsg("You have scanned: " + product.getName());
        List<String> options = new ArrayList();
        options.add("Save product");
        options.add("Save dish");
        options.add("View info");
        options.add("Exit");
        TextUI.promptChoice(options, 1, "Please choose an option: ");
        switch (options.get(0)) {
            case "Save product":
                saveProduct(product);
                break;
            case "Save dish":
                //Todo: Make case for save product to a dish.
            case "View info":
                TextUI.displayMsg(product.toString());
                break;
            case "Exit":
                return;
        }
    }
}