package util;

import Persistens.PoductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductDetailsServiceTest {
    private OpenFoodFactsWrapper mockWrapper;
    private PoductRepo mockProductRepo;

    @BeforeEach
    public void setUp() {
        mockWrapper = Mockito.mock(OpenFoodFactsWrapper.class);
        ProductDetailsService.setWrapper(mockWrapper);
        mockProductRepo = Mockito.mock(PoductRepo.class);
    }

    @Disabled //TODO: Enable when passing
    @Test
    void getProductByCode() {
        // Arrange
        String code = "54491472";
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCode(code);
        productResponse.setProduct(new Product());
        productResponse.setStatus(true);
        when(mockWrapper.fetchProductByCode(code)).thenReturn(productResponse);

        // Act
        Model.Product actual = ProductDetailsService.getProductByCode(code);

        // Assert
        assertEquals(code, actual.getName()); //TODO: Change to getCode()
    }

    @Disabled //TODO: Enable when passing
    @Test
    void getProductByCodeFromDatabase() {
        // Arrange
        String name = "Coca Cola 500ml";
        String code = "54491472";
        double weight = 500;
        int calories = 42;

        ArrayList<Model.Product> products = new ArrayList<>();
        products.add(new Model.Product(name, weight, calories));
        when(mockProductRepo.loadProducts()).thenReturn(products);

        // Act
        Model.Product actual = ProductDetailsService.getProductByCode(code);

        // Assert
        assertEquals(code, actual.getName()); //TODO: Change to getCode()
    }

    @Test
    void getProductByCodeInvalidCode() {
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