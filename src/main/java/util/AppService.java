package util;
import Models.Product;
import Models.User;
import Persistens.ProductRepo;
import java.util.ArrayList;
import java.util.List;

public class AppService {
    private List<String> products;
    User user;
    private List<Product> productList;

    public void setProductList(List<Product> productList) {
        if (productList == null) {
            throw new IllegalArgumentException("Product list cannot be null");
        }
        this.productList = productList;
    }

    public AppService(User user) {
        this.user = user;
        this.productList = new ArrayList<>();
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
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