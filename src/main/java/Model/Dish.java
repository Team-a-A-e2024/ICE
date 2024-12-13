package Model;

import Persistens.DishProductRepo;
import Persistens.DishRepo;
import util.TextUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dish {
    private int id;
    private String name;
    private double dishWeight;
    private int dishCalories;
    List<Product> products;
    public static ArrayList<Dish> loadedDishes = DishRepo.loadDish();

    public Dish(int id, String name, double weight, int dishCalories, List<Product> products){
        this.id = id;
        this.name = name;
        this.dishWeight = weight;
        this.dishCalories = dishCalories;
        this.products = products;
    }

    public Dish(String name, double weight, int dishCalories, List<Product> products) {
        this.name = name;
        this.dishWeight = weight;
        this.dishCalories = dishCalories;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public double getDishWeight() {
        return dishWeight;
    }

    //TODO Make the method to calculate calories of products based on weight!!!
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

    public int getId() {
        return id;
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
        return "Dish: name " + name + " dishWeight " + dishWeight + " dishCalories " + dishCalories + " Products: [" + productNames + "]";
    }

    public static void displayNutritionForAllDishes() {
        ArrayList<Dish> loadedDishes = DishRepo.loadDish();
        System.out.println("Loaded dishes:");
        for (Dish d : loadedDishes) {
            System.out.println(d);
        }
    }

    public static void displayNutritionForSpecificDish() {
        if (!loadedDishes.isEmpty()) {
            // Display all dishes for the user to choose from
            TextUI.displayMsg("Please choose a dish by entering the corresponding number:");
            for (int i = 0; i < loadedDishes.size(); i++) {
                System.out.println((i + 1) + ": " + loadedDishes.get(i).getName());
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
                    System.out.println(p);
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
                System.out.println((i + 1) + ": " + loadedDishes.get(i).getName());
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
            System.out.println("Nutritional values for dish: " + selectedDish.getName());
            System.out.println("Total Calories: " + totalCalories);
            System.out.println("Total Carbs: " + totalCarbs + "g");
            System.out.println("Total Sugar: " + totalSugar + "g");
            System.out.println("Total Protein: " + totalProtein + "g");
            System.out.println("Total Fat: " + totalFat + "g");
        } else {
            System.out.println("No dishes available.");
        }
    }

}

