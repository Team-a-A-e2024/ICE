package Model;

import Persistens.DishProductRepo;
import Persistens.DishRepo;
import util.TextUI;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private int id;
    private String name;
    private double dishWeight;
    private int dishCalories;
    List<Product> products;

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
        System.out.println("Debug: products size = " + products.size()); // Tjek listen
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
        // Retrieve dishes
        ArrayList<Dish> loadedDishes = DishRepo.loadDish();

        if (!loadedDishes.isEmpty()) {
            System.out.println("Loaded dishes: " + loadedDishes.size());

            // Select the first dish for demonstration (or allow user input)
            Dish selectedDish = loadedDishes.get(0);
            int dishId = selectedDish.getId();
            System.out.println("Debug: Selected dish = " + selectedDish.getName() + " with ID = " + dishId);

            // Retrieve products for the dish
            ArrayList<Product> loadedProducts = DishProductRepo.getProductsForDish(dishId);
            System.out.println("Debug: Loaded products for dish = " + loadedProducts.size());

            // Display products
            if (!loadedProducts.isEmpty()) {
                TextUI.displayMsg("Products for dish " + selectedDish.getName() + ":");
                for (Product product : loadedProducts) {
                    System.out.println(product);
                }
            } else {
                TextUI.displayMsg("No products found for dish " + selectedDish.getName());
            }
        } else {
            TextUI.displayMsg("No dishes found.");
        }
    }


    public void displayTotalNutritionForSpecificDish() { //set a users choice parameter
        // Load all dishes
        ArrayList<Dish> loadedDishes = DishRepo.loadDish();

        if (!loadedDishes.isEmpty()) {
            // Choose a dish to calculate nutrition for (e.g., the first dish)
            Dish selectedDish = loadedDishes.get(0); // Replace with user selection if needed
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

