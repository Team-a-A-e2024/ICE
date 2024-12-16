package util;

import Models.Product;
import Persistens.ProductRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static String baseUrl = "https://world.openfoodfacts.net";
    private static HttpClient client = HttpClient.newHttpClient();
    private static List<Product> products = ProductRepo.loadProducts();

    //searches for a list of products through the openfoodfacts api
    //page number is indexed at 1
    //a page number higher than the amount of products returns an empty arraylist of products
    public static List<Product> searchProduct(String searchString, int page) {
        //makes a call to the openfoodfacts api to search for a product based on searchString parameter
        String searchRequest = baseUrl + "/api/v2/search" +
                "?categories_tags_en=" + encodeValue(searchString) +
                "&fields=product_name," +
                "code," +
                "product_quantity," +
                "energy-kcal_100g," +
                "carbohydrates_100g," +
                "sugars_100g," +
                "proteins_100g," +
                "fat_100g" +
                "&page_size=100" +
                "&page=" + page +
                "&sort_by=popularity_key";

        String responseBody = GET(searchRequest);
      

        return productParser(responseBody);
    }

    private static List<Product> productParser(String responseBody) {
        ApiService.products = ProductRepo.loadProducts();
        List<Product> products = new ArrayList<>();

        //converts response to json and gets the Code value from each element in the array of products
        JSONObject obj = new JSONObject(responseBody);
        JSONArray arr = obj.getJSONArray("products");

        for (int i = 0; i < arr.length(); i++)
        {
            String code = arr.getJSONObject(i).getString("code");

            try {
                String productName = arr.getJSONObject(i).getString("product_name");
                if (productName == null || productName.isEmpty() || productName.isBlank()){
                    continue;
                }
                else if (!isProductSaved(code)) {
                    Product product = new Product(
                            arr.getJSONObject(i).getString("product_name"),
                            arr.getJSONObject(i).getString("code"),
                            arr.getJSONObject(i).getBigDecimal("product_quantity").doubleValue(),
                            arr.getJSONObject(i).getBigDecimal("energy-kcal_100g").intValue(),
                            arr.getJSONObject(i).getBigDecimal("carbohydrates_100g").intValue(),
                            arr.getJSONObject(i).getBigDecimal("sugars_100g").intValue(),
                            arr.getJSONObject(i).getBigDecimal("proteins_100g").intValue(),
                            arr.getJSONObject(i).getBigDecimal("fat_100g").intValue()
                    );

                    ProductRepo.saveProduct(product);
                }
            } catch (Exception e) {

            }
        }

        return products;
    }

    private static String GET(String searchRequest) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchRequest))
                .GET() // Default is GET
                .build();
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join(); // Waits for the response

        return response.body();
    }

    //is used to sanitize user input to be enterable into an url
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            TextUI.displayMsg(e.getMessage());
        }
        return "";
    }

    private static boolean isProductSaved(String code) {
        return products.stream().anyMatch(x -> x.getBarcode().equalsIgnoreCase(code));
    }
}