package util;

import Model.Product;
import enums.DishCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//entire class copied from SP3

public class TextUI {
    private static Scanner scan = new Scanner(System.in);

    private TextUI(){};

    public static void displayMsg(String msg){
        System.out.println(msg);
    }

    // Gives the user an option to either press 'y' to continue or 'n' to return to message
    public static boolean promptBinary(String msg){
        String input = promptText(msg);
        if(input.equalsIgnoreCase("Y")){
            return true;
        }
        else if(input.equalsIgnoreCase("N")){
            return false;
        }
        return promptBinary(msg);
    }

    // Gives the user an option to press an integer to move forward which returns a number
    public static int promptNumeric(String msg) {
        displayMsg(msg);
        String input = scan.nextLine();
        int number;

        try {
            number = Integer.parseInt(input);
        }
        catch(NumberFormatException e){
            displayMsg("Please type a number");
            number = promptNumeric(msg);
        }
        return number;
    }

    public static String promptText(String msg){
        displayMsg(msg);
        return scan.nextLine();
    }

    // This method doesn't prevent selecting the same option multiple times.
    // User choices are one-indexed, i.e., selecting `1` corresponds to the first item in the options list.
    public static List<String> promptChoice(List<String> options, int limit, String msg){
        List<String> choices = new ArrayList<>();
        // The `displayList()` function is used to present options to the user.
        displayList(options, msg);

        while(choices.size() < limit){
            // The `promptNumeric()` function is used to capture numeric input from the user.
            int choice = promptNumeric("");

            if (choice > 0 && choice <= options.size()) {
                choices.add(options.get(choice-1));
            }
        }
        return choices;
    }

    public static List<Product> promptChoiceProductsForDish(List<Product> options, int limit, String msg){
        List<Product> choices = new ArrayList<>();

        boolean state = true;
        while(state){
            // The `displayList()` function is used to present options to the user.
            displayListOfProducts(options, msg);
            // The `promptNumeric()` function is used to capture numeric input from the user.
            int choice = promptNumeric("");

            if (choice > 0 && choice <= options.size()) {
                choices.add(options.get(choice-1));
            }
            state = TextUI.promptBinary("Do you want to add more products? Enter Y or N");
        }
        return choices;
    }
    // The method prints the provided message.
    // It then iterates through the list, printing each option with a phased index starting from 1.
    public static void displayList(List<String> options, String msg){
        displayMsg("");
        displayMsg(msg);
        displayMsg("");

        int i = 1;

        for (String option : options) {
            System.out.println(i+": "+option);
            i++;
        }
    }

    public static void displayListOfProducts(List<Product> options, String msg){
        displayMsg("");
        displayMsg(msg);
        displayMsg("");

        int i = 1;

        for (Product option : options) {
            displayMsg(i+": "+option);
            i++;
        }
    }

    public static void setScanner(Scanner scanner) {
        scan = scanner;
    }

    public static double promptDouble(String msg){
        displayMsg(msg);
        return scan.nextDouble();
    }

    public static int promptInt(String msg){
        displayMsg(msg);
        return scan.nextInt();
    }

    public static void clearInputBuffer() {
        if (scan.hasNextLine()) {
            scan.nextLine(); // Clears the buffer
        }
    }

    public static DishCategory promptEnum(String msg){
        int i = 1;
        for(DishCategory category : DishCategory.values()){
            displayMsg(i+"."+category);
            i++;
        }

        boolean state = true;
        while(state){
            // The `promptNumeric()` function is used to capture numeric input from the user.
            int choice = promptNumeric(msg);

            if (choice >= 1 && choice <= DishCategory.values().length) {
                return DishCategory.values()[choice-1];// Return the selected enum constant.
            } else {
                displayMsg("Invalid choice. Please select a valid number.");
            }
        }
        return DishCategory.values()[0];
    }
}