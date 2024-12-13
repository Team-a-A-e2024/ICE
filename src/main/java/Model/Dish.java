package Model;

import enums.DishCategory;

import java.util.List;

public class Dish {
    private int id;
    private String name;
    private double dishWeight;
    private int dishCalories;
    private DishCategory dishCategory;
    List<Product> products;

    public Dish(String name, double weight, int dishCalories, List<Product> products, DishCategory dishCategory) {
        this.name = name;
        this.dishWeight = weight;
        this.dishCalories = dishCalories;
        this.products = products;
        this.dishCategory = dishCategory;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
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

    public int getId() {
        return id;
    }
}
