import Model.Dish;
import Model.Product;
import Model.User;
import Persistens.DishRepo;
import Persistens.SeedDB;
import util.Authorization;
import Persistens.ProductRepo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        //SeedDB.createDB();
//        auth.signUp("Casper", "123");
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(banan);
//        products.add(jordbær);
//        products.add(mælk);
//
//
//        Dish dish = new Dish("Smoothie",500, 500, products);

        User user = new User("Alissa", "123");

        ArrayList<Dish> dishes = DishRepo.loadDish();
        for (Dish d : dishes) {
            System.out.println(d.toString());
        }
//        DishRepo.saveDish(dish);

    }
}
