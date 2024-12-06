package User;

import pl.coderion.model.Product;
import java.util.HashMap;
import java.util.Map;

public class Dish {
protected Map<pl.coderion.model.Product, Float> ingredients;

public Dish(Map<pl.coderion.model.Product, Float> ingredients) {

    this.ingredients = new HashMap<pl.coderion.model.Product, Float>();

    }

    public Map<Product, Float> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Product, Float> ingredients) {
        this.ingredients = ingredients;
    }
}


