package user;


import pl.coderion.model.Product;
import user.Dish;
import java.util.Set;

public class User {

    private Set<String> Allergens;
    private Set<Dish> Dishes;
    private Set<Product> Products;

    public Set<String> getAllergens() {
        return Allergens;
    }

    public void setAllergens(Set<String> allergens) {
        Allergens = allergens;
    }

    public Set<Dish> getDishes() {
        return Dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        Dishes = dishes;
    }

    public void addDishes(Dish dish) {
        Dishes.add(dish);
    }

    public Set<Product> getProducts() {
        return Products;
    }

    public void setProducts(Set<Product> products) {
        Products = products;
    }
    public void addProduct(Product product) {
        Products.add(product);
    }
}
