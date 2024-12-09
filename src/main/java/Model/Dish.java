package Model;

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

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder productNames = new StringBuilder();
        for (Product product : products) {
            productNames.append(product.toString()).append(", ");
        }
        // Remove trailing comma and space
        if (productNames.length() > 0) {
            productNames.setLength(productNames.length() - 2);
        }
        return "Dish: name" + name + " " + "dishWeight" + dishWeight + "dishCalories" +  dishCalories + productNames;

    }

}
