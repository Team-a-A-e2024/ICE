import Model.Dish;
import Model.Product;
import Persistens.DishRepo;
import Persistens.SeedDB;
import enums.DishCategory;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("carrots", "4040", 50,50,50,50,50,50));
        products.add(new Product("iceberg", "40670", 50,50,50,50,50,50));
        products.add(new Product("breadCrumbs", "45085", 50,50,50,50,50,50));


        Dish dish = new Dish("Vegan Salad", 300, 150, products, DishCategory.BREAKFAST);
        DishRepo.saveDish(dish);

    }
}

