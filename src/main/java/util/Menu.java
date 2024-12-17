package util;

import Models.Product;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static Models.Dish.*;
import static Persistens.ProductRepo.saveProduct;
import static barscanner.Barscanner.readCodeFromPath;
import static java.lang.System.exit;
import static util.ApiService.searchProduct;
import static util.ApiService.searchProductByCode;
import static util.TextUI.promptChoiceProducts;

public class Menu {

    public static void loginMenuLogic() {
        //Asks the user if they want to log in or sign-up
        List<String> options = new ArrayList();
        boolean loggedIn = false;
        options.add("Log in");
        options.add("Sign up");
        options = TextUI.promptChoice(options, 1, "There's no user logged in");

        //Depending on what they want to do it runs different functions for the login and sign-up function
        Authorization auth = new Authorization();
        switch (options.get(0)) {
            case "Log in":
                String username = TextUI.promptText("Please enter your username: ");
                String password = TextUI.promptText("Please enter your password: ");
                loggedIn = auth.login(username, password);
                break;
            case "Sign up":
                username = TextUI.promptText("Please create a new username: ");
                password = TextUI.promptText("Please create a new password: ");
                auth.signUp(username, password);
                break;
        }

        //If there's no user, it runs itself
        if (loggedIn == false) {
            loginMenuLogic();
        }else{
            displayMenu();
        }
    }

    public static void displayMenu() {
        List<String> options = new ArrayList();
        options.add("Search products");
        options.add("View saved dishes");
        options.add("Create a new dish");
        options.add("Scan barcode");
        options.add("Exit");
        options = TextUI.promptChoice(options, 1, "What would you like to do?");
        Set<Menu> result = new HashSet<>();
        switch (options.get(0)) {
            case "Search products":
                searchProductMenu();
                break;
            case "View saved dishes":
                viewDishesMenu();
                break;
            case "Create a new dish":
                createDishByUser();
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
                product = searchProductByCode(readCodeFromPath(filePath));
                productMenu(product);
                break;
            case "Enter barcode manually":
                String typeBarcode = TextUI.promptText("Please type the barcode number you wish to scan: ");
                product = searchProductByCode(typeBarcode);
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
        String search = TextUI.promptText("Please search for a product: ");
        List <Models.Product> result = searchProduct(search,1);
        List <Models.Product> choice  = promptChoiceProducts(result, 1, "You have found, please choose a product");
        productMenu(choice.get(0));
    }

    public static void viewDishesMenu() {
        displayNutritionForAllDishes();
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
                createDishByUser();
            case "View info":
                TextUI.displayMsg(product.toString());
                break;
            case "Exit":
        }
    }
}