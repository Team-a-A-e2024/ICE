import Model.Product;
import Model.User;
import Persistens.ProductRepo;
import util.TextUI;
import java.util.ArrayList;
import java.util.List;

public class AppService {
    private List<String> products;
    User user;
    private ProductRepo productRepo;
    private ArrayList<Product> productList;

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public AppService(ProductRepo productRepo) {
        this.productRepo = productRepo;
        products = new ArrayList<>();
    }

    public ArrayList<Product> searchProducts() {
        ArrayList<Product> results = new ArrayList<>();
        TextUI.displayMsg("Please enter the product you wish to search");
        String productName = TextUI.promptText("Search for a product");

        for (Product p : productList) {
            if (p.getName().toLowerCase().contains(productName.toLowerCase())) {
                results.add(p);
            }
        }
        if (productName.equalsIgnoreCase("x")) {
            TextUI.displayMsg("You decided not to search, closing searching..");
            return results;
        }
        if (results.isEmpty()) {
            TextUI.displayMsg(productName + " not found, try again");
            return searchProducts();
        }
            return results;
    }
}