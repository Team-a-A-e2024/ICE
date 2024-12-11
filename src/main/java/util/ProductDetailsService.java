package util;

import Model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

import java.util.ArrayList;

public class ProductDetailsService {
    private static OpenFoodFactsWrapper wrapper = new OpenFoodFactsWrapperImpl();
    private static ArrayList<Product> products = new ArrayList<>();

    private ProductDetailsService() {}

    public static void setWrapper(OpenFoodFactsWrapper wrapper) {
        ProductDetailsService.wrapper = wrapper;
    }

    public static void setProducts(ArrayList<Product> products) {
        ProductDetailsService.products = products;
    }

    /*
    ------------------------------ SUMMARY ------------------------------
    Retrieves a product object or a null object.

    The method will try to find a matching product from database - If
    unsuccessful, it will try to find a matching product from
    OpenFoodFacts. If neither sources can provide a product, it will
    return null.
    ---------------------------------------------------------------------
     */
    public static Product getProductByCode(String code) {
        Product product = getLocalProductByCode(code);

        if (product != null) {
          return product;
        }

        product = getOpenFoodFactsProductByCode(code);

        if (product != null) {
            // TODO: Save product to database.
        }

        return product;
    }

    /*
    ------------------------------ SUMMARY ------------------------------
    Retrieves a product object or a null object.

    The method will try to find a matching product from OpenFoodFacts.
    If the wrapper fails to provide a product, it will return null.
    ---------------------------------------------------------------------
     */
    private static Product getOpenFoodFactsProductByCode(String code) {
        ProductResponse productResponse = wrapper.fetchProductByCode(code);

        if (!productResponse.isStatus()) {
            TextUI.displayMsg("No products with code '" + code + "' was found.");
            return null;
        }

        return productMapper(productResponse.getProduct());
    }

    /*
    ------------------------------ SUMMARY ------------------------------
    Retrieves a product object or a null object.

    The method will try to find a matching product from database.
    If the stream fails to provide a product, it will return null.
    ---------------------------------------------------------------------
     */
    private static Product getLocalProductByCode(String code) {
        assert products != null;
        return products.stream()
                .filter(x -> x.getName().equals(code)) //TODO: Change to getCode()
                .findFirst()
                .orElse(null);
    }

    /*
    ------------------------------ SUMMARY ------------------------------
    Maps a OpenFoodFact product to our Product model.
    ---------------------------------------------------------------------
     */
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