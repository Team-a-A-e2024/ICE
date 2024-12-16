package Models;

import Persistens.ProductRepo;
import util.TextUI;

import java.util.InputMismatchException;

public class Product {
    private int id;
    private String name;
    private String barcode;
    private double weight;
    private int calorie;
    private int carbs;
    private int sugar;
    private int protein;
    private int fat;

    public Product(String name, String barcode, double weight, int calories, int carbs, int sugar, int protein, int fat) {
        this.name = name;
        this.barcode = barcode;
        this.weight = weight;
        this.calorie = calories;
        this.carbs = carbs;
        this.sugar = sugar;
        this.protein = protein;
        this.fat = fat;
    }

    public Product(int id, String name, String barcode, double weight, int calories, int carbs, int sugar, int protein, int fat) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.weight = weight;
        this.calorie = calories;
        this.carbs = carbs;
        this.sugar = sugar;
        this.protein = protein;
        this.fat = fat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String trim) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getCarb() {
        return carbs;
    }

    public void setCarb(int carbs) {
        this.carbs = carbs;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Product: name = " + name + "  " + "weight " + weight + "  "+ "calories " + calorie;
    }

    public static void createProductByUser() {

        boolean succes = false;

        while (!succes)
            try {
                String name = TextUI.promptText("Enter the name of the product:");
                String barcode = TextUI.promptText("Enter the barcode of the product:");
                double weight = TextUI.promptDouble("Enter the weight of the product (in grams):");
                int calorie = TextUI.promptInt("Enter the number of calories in the product:");
                int carb = TextUI.promptInt("Enter the carbohydrate content of the product (in grams):");
                int sugar = TextUI.promptInt("Enter the sugar content of the product (in grams):");
                int protein = TextUI.promptInt("Enter the protein content of the product (in grams):");
                int fat = TextUI.promptInt("Enter the fat content of the product (in grams):");

                Product product = new Product(name, barcode, weight, calorie, carb, sugar, protein, fat);
                ProductRepo.saveProduct(product);
                TextUI.displayMsg("Product added successfully");
                succes = true;

            } catch (InputMismatchException iME) {
                TextUI.displayMsg("Invalid input! Please ensure you enter the correct type of value. Try again");
                // Clear the invalid input from the buffer
                TextUI.clearInputBuffer();
            }
    }
}
