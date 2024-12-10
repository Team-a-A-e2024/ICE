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
    public static ArrayList<String> searchProduct(String searchString, int page) {
        String searchRequest = "https://world.openfoodfacts.net/api/v2/search?categories_tags_en=" + encodeValue(searchString) + "&fields=code&page_size=100&page="+page+"&sort_by=popularity_key&countries_tags_en=denmark";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchRequest))
                .GET() // Default is GET
                .build();
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join(); // Waits for the response

        JSONObject obj = new JSONObject(response.body());
        JSONArray arr = obj.getJSONArray("products");

        ArrayList<String> products = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++)
        {
            products.add(arr.getJSONObject(i).getString("code"));
        }
        return products;
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            //todo: not this
            TextUI.displayMsg(e.getMessage());
        }
        return "";
    }
}
