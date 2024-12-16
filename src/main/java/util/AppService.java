package util;

import Model.Product;
import Model.User;
import Persistens.ProductRepo;

import java.util.ArrayList;
import java.util.List;

public class AppService {
    private List<String> products;
    User user;
    private List<Product> productList;

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public AppService(ProductRepo poductRepo) {
        this.productRepo = poductRepo;
        products = new ArrayList<>();
    }

    //searches for a product within an array
    public ArrayList<Product> searchProducts() {
        ArrayList<Product> results = new ArrayList<>();
        TextUI.displayMsg("Please enter the product you wish to search");
        String productName = TextUI.promptText("Search for a product");

        //sees if the product has the same name and adds it to a list
        for (Product p : productList) {
            if (p.getName().toLowerCase().contains(productName.toLowerCase())) {
                results.add(p);
            }
        }
        //entering only X quits
        if (productName.equalsIgnoreCase("x")) {
            TextUI.displayMsg("You decided not to search, closing searching..");
            return results;
        }
        //if search didn't find anything respond properly
        if (results.isEmpty()) {
            TextUI.displayMsg(productName + " not found, try again");
            return searchProducts();
        }
            return results;
    }
}