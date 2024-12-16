
import Models.Product;
import Persistens.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.AppService;
import util.TextUI;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import Models.User;

class AppServiceTest {
    private Scanner mockScanner;
    private User mockUser;

@BeforeEach
void setup(){

    mockScanner = mock(Scanner.class);
    TextUI.setScanner(mockScanner);
    mockUser = mock(User.class);
}
@Test
    void testAppService() {

    // arrange
    when(mockScanner.nextLine()).thenReturn("Coca-Cola");
    Product product = new Product("Coca-Cola","330",120,50,50,50,50,0);
    ArrayList<Product> nyCocaCola = new ArrayList<Product>();
    nyCocaCola.add(product);
    // act
    AppService appservice = new AppService(mockUser);
    appservice.setProductList(nyCocaCola);
    ArrayList<Product> actual = appservice.searchProducts();
    //assert
    assertEquals(product.getName(), actual.get(0).getName());
    }

@Test
void testShortCutSearching() {

    // arrange
    when(mockScanner.nextLine()).thenReturn("Coca");
    Product product = new Product("Coca-Cola","330",120,50,50,50,50,0);
    ArrayList<Product> nyCocaCola = new ArrayList<Product>();
    nyCocaCola.add(product);
    // act
    AppService appservice = new AppService(mockUser);
    appservice.setProductList(nyCocaCola);
    ArrayList<Product> actual = appservice.searchProducts();
    //assert
    assertEquals(product.getName(), actual.get(0).getName());
    }

@Test
    void testProductNotFound() {

        // arrange
        when(mockScanner.nextLine()).thenReturn("mælk", "Coca-Cola");
        Product product = new Product("Coca-Cola","330",120,50,50,50,50,0);
        ArrayList<Product> nyCocaCola = new ArrayList<Product>();
        nyCocaCola.add(product);
        // act
        AppService appservice = new AppService(mockUser);
        appservice.setProductList(nyCocaCola);
        ArrayList<Product> actual = appservice.searchProducts();
        //assert
        assertEquals(product.getName(), actual.get(0).getName());
    }

    @Test
    void testClosingSearching() {
        when(mockScanner.nextLine()).thenReturn("x");
        // act
        AppService appservice = new AppService(mockUser);
        appservice.setProductList(new ArrayList<Product>());
        ArrayList<Product> actual = appservice.searchProducts();
        //assert
        assertEquals(0, actual.size());
    }
}