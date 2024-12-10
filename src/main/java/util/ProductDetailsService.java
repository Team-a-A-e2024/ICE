package util;

import Model.Product;
import Persistens.PoductRepo;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

import java.util.ArrayList;

public class ProductDetailsService {
    private static OpenFoodFactsWrapper wrapper = new OpenFoodFactsWrapperImpl();
    private static ArrayList<Product> products;

    private ProductDetailsService() {}

    static {
        products = PoductRepo.loadProducts();
    }

    public static OpenFoodFactsWrapper getWrapper() {
        return wrapper;
    }

    public static void setWrapper(OpenFoodFactsWrapper wrapper) {
        ProductDetailsService.wrapper = wrapper;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        ProductDetailsService.products = products;
    }

    public static Product getProductByCode(String code) {
        Product product = getCachedProductByCode(code);
        ProductResponse productResponse;

        if (product != null) {
          return product;
        }

        productResponse = wrapper.fetchProductByCode(code);

        if (!productResponse.isStatus()) {
            TextUI.displayMsg("No products with code '" + code + "' was found.");
            return null;
        }

        product = productMapper(productResponse.getProduct());

        if (product != null) {
            products.add(product);
            // TODO: Save product to database.
        }

        return product;
    }

    private static Product getCachedProductByCode(String code) {
        return products.stream()
                .filter(x -> x.getName().equals(code)) //TODO: Change to getCode()
                .findFirst()
                .orElse(null);
    }

    private static Product productMapper(pl.coderion.model.Product product) {
        String productName = product.getProductName();
        double productWeight = Double.parseDouble(product.getProductQuantity());
        int productCalories = product.getNutriments().getEnergyKcalServing();

        if (productName == null || productName.isEmpty() || productName.isBlank()) {
            return null;
        }

        return new Product(productName, productWeight, productCalories);
    }
}
