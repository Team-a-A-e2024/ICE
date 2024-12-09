package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private String password;
    List<String> allergens;
    List<Product> products;
    List<Dish> dishes;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.allergens = new ArrayList<>();
        this.products = new ArrayList<>();
        this.dishes = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void addAllergens(String allergy) {
        allergens.add(allergy);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(Product product) {
        products.add(product);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDishes(Dish dish) {
        dishes.add(dish);
    }
}
