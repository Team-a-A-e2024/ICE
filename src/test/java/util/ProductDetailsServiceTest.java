package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderion.model.Nutriments;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductDetailsServiceTest {
    private OpenFoodFactsWrapper mockWrapper;

    @BeforeEach
    public void setUp() {
        mockWrapper = Mockito.mock(OpenFoodFactsWrapper.class);
        ProductDetailsService.setWrapper(mockWrapper);
    }

    @Test
    void when_searching_for_valid_code_then_return_openFoodFact_product() {
        // Arrange
        String code = "54491472";

        Nutriments nutriments = new Nutriments();
        nutriments.setEnergyKcalServing(210);
        nutriments.setCarbohydratesServing(53);
        nutriments.setSugarsServing(53);
        nutriments.setProteinsServing(0);
        nutriments.setFatServing(0);

        Product product = new Product();
        product.setCode(code);;
        product.setProductName("Coca Cola 500ml");
        product.setProductQuantity("500");
        product.setNutriments(nutriments);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setCode(code);
        productResponse.setProduct(product);
        productResponse.setStatus(true);

        when(mockWrapper.fetchProductByCode(code)).thenReturn(productResponse);

        // Act
        Model.Product actual = ProductDetailsService.getProductByCode(code);

        // Assert
        assertEquals(code, actual.getBarcode());
    }

    @Test
    void when_searching_for_valid_code_then_return_database_product() {
        // Arrange
        String name = "Coca Cola 500ml";
        String code = "54491472";
        double weight = 500;
        int calories = 42;
        int carbs = 53;
        int sugar = 53;
        int protein = 0;
        int fat = 0;

        ArrayList<Model.Product> products = new ArrayList<>();
        products.add(new Model.Product(name, code, weight, calories, carbs, sugar, protein, fat));
        ProductDetailsService.setProducts(products);

        // Act
        Model.Product actual = ProductDetailsService.getProductByCode(code);

        // Assert
        assertEquals(code, actual.getBarcode());
    }

    @Test
    void when_searching_for_invalid_() {
        // Arrange
        String code = "xyz";
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus(false);
        when(mockWrapper.fetchProductByCode(code)).thenReturn(productResponse);

        // Act
        Model.Product actual = ProductDetailsService.getProductByCode(code);

        // Assert
        assertNull(actual);
    }
}