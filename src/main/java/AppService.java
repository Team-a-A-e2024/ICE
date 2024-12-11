import Model.Product;
import Model.User;
import Persistens.PoductRepo;
import util.TextUI;
import java.util.ArrayList;
import java.util.List;

public class AppService {
    private List<String> products;
    User user;
    private PoductRepo poductRepo;

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    private ArrayList<Product> productList;

    public AppService(PoductRepo poductRepo) {
        this.poductRepo = poductRepo;
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