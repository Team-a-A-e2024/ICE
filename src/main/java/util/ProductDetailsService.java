package util;

import Model.Product;
import Persistens.ProductRepo;
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
            ProductRepo.saveProduct(product);
            products = ProductRepo.loadProducts();
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
        if (products.isEmpty()) {
            products = ProductRepo.loadProducts();
        }

        return products.stream()
                .filter(x -> x.getBarcode().equals(code))
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

        if (productName == null || productName.isEmpty() || productName.isBlank()) {
            return null;
        }

        return new Product(
                product.getProductName(),
                product.getCode(),
                Double.parseDouble(product.getProductQuantity()),
                product.getNutriments().getEnergyKcalServing(),
                (int)product.getNutriments().getCarbohydratesServing(),
                (int)product.getNutriments().getSugarsServing(),
                (int)product.getNutriments().getProteinsServing(),
                (int)product.getNutriments().getFatServing()
        );
    }
}
