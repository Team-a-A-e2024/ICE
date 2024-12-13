package util;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ApiService {
    //searches for a list of products through the openfoodfacts api
    //page number is indexed at 1
    //a page number higher than the amount of products returns an empty arraylist of products
    public static ArrayList<String> searchProduct(String searchString, int page) {
        //makes a call to the openfoodfacts api to search for a product based on searchString parameter
        String searchRequest = "https://world.openfoodfacts.net/api/v2/search?categories_tags_en=" + encodeValue(searchString) + "&fields=code&page_size=100&page="+page+"&sort_by=popularity_key&countries_tags_en=denmark";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchRequest))
                .GET() // Default is GET
                .build();
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join(); // Waits for the response
      
        //converts response to json and gets the Code value from each element in the array of products
        JSONObject obj = new JSONObject(response.body());
        JSONArray arr = obj.getJSONArray("products");
        ArrayList<String> products = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++)
        {
            products.add(arr.getJSONObject(i).getString("code"));
        }
        return products;
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
}