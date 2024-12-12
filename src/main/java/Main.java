import Model.Dish;
import Model.Product;
import Persistens.DishRepo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        Dish dish = new Dish("Pandekage",400,200, products);
        products.add(new Product("Mel","0203",400,400,400,400,400,4));
        products.add(new Product("Sukker","0203",400,400,400,400,400,4));
        products.add(new Product("Vanilje","0203",400,400,400,400,400,4));
        DishRepo.saveDish(dish);
        dish.displayNutrtion();


    }
}

