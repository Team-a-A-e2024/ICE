package Models;

import Persistens.DishProductRepo;
import Persistens.DishRepo;
import Persistens.ProductRepo;
import util.TextUI;
import Models.enums.DishCategory;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class Dish {
    private int id;
    private String name;
    private double dishWeight;
    private int dishCalories;
    private DishCategory dishCategory;
    List<Product> products;
    public static ArrayList<Dish> loadedDishes = DishRepo.loadDish();


    public Dish(int id, String name, double weight, int dishCalories, List<Product> products, DishCategory dishCategory) {
        this.id = id;
        this.name = name;
        this.dishWeight = weight;
        this.dishCalories = dishCalories;
        this.products = products;
        this.dishCategory = dishCategory;
    }

    public Dish(String name, double weight, int dishCalories, List<Product> products, DishCategory dishCategory) {
        this.name = name;
        this.dishWeight = weight;
        this.dishCalories = dishCalories;
        this.products = products;
        this.dishCategory = dishCategory;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public String getName() {
        return name;
    }

    public double getDishWeight() {
        return dishWeight;
    }

    public int getDishCalories() {
        return dishCalories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDishWeight(double dishWeight) {
        this.dishWeight = dishWeight;
    }

    public void setDishCalories(int dishCalories) {
        this.dishCalories = dishCalories;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {

        StringBuilder productNames = new StringBuilder();
        for (Product product : products) {
            productNames.append(product.toString()).append(", ");
        }
        // Remove trailing comma and space
        if (!productNames.isEmpty()) {
            productNames.setLength(productNames.length() - 2);
        }
        return "Dish: name " + name + " dishWeight " + dishWeight + " dishCalories " + dishCalories +  "Products: [" + productNames + "]";
    }

    public static void displayNutritionForAllDishes() {
        ArrayList<Dish> loadedDishes = DishRepo.loadDish();
        TextUI.displayMsg("Loaded dishes:");
        for (Dish d : loadedDishes) {
            System.out.println(d);
        }
    }

    public static void displayNutritionForSpecificDish() {
        if (!loadedDishes.isEmpty()) {
            // Display all dishes for the user to choose from
            TextUI.displayMsg("Please choose a dish by entering the corresponding number:");
            for (int i = 0; i < loadedDishes.size(); i++) {
                TextUI.displayMsg((i + 1) + ": " + loadedDishes.get(i).getName());
            }

            // Get user input
            Scanner scanner = new Scanner(System.in);
            int choice = -1;
            while (choice < 1 || choice > loadedDishes.size()) {
                TextUI.displayMsg("Enter a valid number between 1 and " + loadedDishes.size() + ":");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next(); // Consume invalid input
                }
            }

            Dish selectedDish = loadedDishes.get(choice - 1);

            // Retrieve and display products for the selected dish
            ArrayList<Product> loadedProducts = DishProductRepo.getProductsForDish(selectedDish.getId());

            if (!loadedProducts.isEmpty()) {
                TextUI.displayMsg("Products for dish " + selectedDish.getName() + ":");
                for (Product p : loadedProducts) {
                    TextUI.displayMsg("" + p);
                }
            } else {
                TextUI.displayMsg("No products found for dish " + selectedDish.getName() + ".");
            }
        } else {
            TextUI.displayMsg("No dishes found.");
        }
    }

    public static void displayTotalNutritionForSpecificDish() {
        if (!loadedDishes.isEmpty()) {
            // Display all dishes for the user to choose from
            TextUI.displayMsg("Please choose a dish by entering the corresponding number:");
            for (int i = 0; i < loadedDishes.size(); i++) {
                TextUI.displayMsg((i + 1) + ": " + loadedDishes.get(i).getName());
            }

            // Get user input
            Scanner scanner = new Scanner(System.in);
            int choice = -1;
            while (choice < 1 || choice > loadedDishes.size()) {
                TextUI.displayMsg("Enter a valid number between 1 and " + loadedDishes.size() + ":");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next(); // Consume invalid input
                }
            }

            Dish selectedDish = loadedDishes.get(choice - 1);
            int dishId = selectedDish.getId();

            // Retrieve products for the selected dish
            ArrayList<Product> loadedProducts = DishProductRepo.getProductsForDish(dishId); //TODO Replace dishId or make a method where we can see dishId before selecting a dish

            double totalCalories = 0;
            int totalCarbs = 0;
            int totalSugar = 0;
            int totalProtein = 0;
            int totalFat = 0;

            for (Product product : loadedProducts) {
                totalCalories += product.getCalorie();
                totalCarbs += product.getCarb();
                totalSugar += product.getSugar();
                totalProtein += product.getProtein();
                totalFat += product.getFat();
            }

            // Display results
            TextUI.displayMsg("Nutritional values for dish: " + selectedDish.getName());
            TextUI.displayMsg("Total Calories: " + totalCalories);
            TextUI.displayMsg("Total Carbs: " + totalCarbs + "g");
            TextUI.displayMsg("Total Sugar: " + totalSugar + "g");
            TextUI.displayMsg("Total Protein: " + totalProtein + "g");
            TextUI.displayMsg("Total Fat: " + totalFat + "g");
        } else {
            TextUI.displayMsg("No dishes available.");
        }
    }

    public static double calculateTotalWeightForADish(List<Product> products) {

            int totalWeight = 0;

            for (Product product : products) {
                totalWeight += product.getWeight();
            }
            // Display results
            return totalWeight;
    }

    public static int calculateTotalCalorieForADish(List<Product> products) {

            int totalCalories = 0;

            for (Product product : products) {
                totalCalories += product.getCalorie();;
            }
            return totalCalories;
    }

    public int getId() {
        return id;
    }

    public static void createDishByUser() {

        List<Product> ListOfProducts = ProductRepo.loadProducts();
        List<Product> choices = TextUI.promptChoiceProductsForDish(ListOfProducts, 20, "Which products would you like to add to your dish?");

        String name = TextUI.promptText("What is the name of the dish?");
        DishCategory dishCategory = TextUI.promptEnum("Which kind of meal is the dish?");

        double totalWeight = Dish.calculateTotalWeightForADish(choices);
        int totalCalories = Dish.calculateTotalCalorieForADish(choices);

        Dish dish = new Dish(name, totalWeight, totalCalories, choices, dishCategory);
        DishRepo.saveDish(dish);
    }
}