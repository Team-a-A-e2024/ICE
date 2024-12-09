import Model.Dish;
import Model.Product;
import Persistens.DishRepo;
import Persistens.SeedDB;
import util.Authorization;
import Persistens.ProductRepo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

//        Authorization auth = new Authorization();
        //SeedDB.createDB();
//        auth.signUp("Casper", "123");
//
//        ProductRepo.saveProduct(banan);
//        Product banan = new Product("Banan", 50, 100);
//        Product jordbær = new Product("jordbær", 70, 20);
//        Product mælk = new Product("mælk", 80, 120);
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(banan);
//        products.add(jordbær);
//        products.add(mælk);
//
//
//        Dish dish = new Dish("Smoothie",500, 500, products);

        ArrayList<Dish> dishes = DishRepo.loadDish();
        for(Dish d : dishes) {
            System.out.println(d.toString());
        }
//        DishRepo.saveDish(dish);

    }
}
