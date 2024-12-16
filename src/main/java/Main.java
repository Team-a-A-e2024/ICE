import Model.Dish;
import Model.Product;
import Persistens.ProductRepo;
import Persistens.SeedDB;
import com.beust.ah.A;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = ProductRepo.loadProducts();
        products.add(new Product("Banana", "86948", 50,100,90,5,2,2));
        products.add(new Product("Strawberry", "94376", 51,90,5,2,5,2));

        Dish.createDishByUser();
    }
}

