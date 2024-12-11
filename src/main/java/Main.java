import Model.Dish;
import Model.Product;
import Persistens.DishProductRepo;
import Persistens.DishRepo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Seed database
//        SeedDB.createDB();

//        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product("Banana", "913198", 50, 50, 50, 50, 50, 50));
//        products.add(new Product("Strawberry", "913199", 30, 20, 15, 10, 5, 3));

//        Dish dish = new Dish("Smoothie", 400, 500, products);
//
        ArrayList<Dish> loadedDishes = DishRepo.loadDish();
        System.out.println("Loaded dishes:");
        for (Dish d : loadedDishes) {
            System.out.println(d);
        }

        // Retrieve products for a specific dish
        if (!loadedDishes.isEmpty()) {
            int dishId = loadedDishes.get(0).getId(); // Assuming Dish has an `id` field
            ArrayList<Product> loadedProducts = DishProductRepo.getProductsForDish(dishId);
            System.out.println("Products for dish " + loadedDishes.get(0).getName() + ":");
            for (Product p : loadedProducts) {
                System.out.println(p);
            }
        }
    }
}

