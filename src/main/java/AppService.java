import java.util.ArrayList;
import java.util.List;

public class AppService {
    private List<String> products;

    public AppService() {
        products = new ArrayList<>();
    }

    public void addProduct(String product) {
        products.add(product);
    }

    public List<String> searchProducts(String searchTerm) {
        List<String> results = new ArrayList<>();
        for (String product : products) {
            if (product.toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(product);
            }
        }
        return results;
    }
}