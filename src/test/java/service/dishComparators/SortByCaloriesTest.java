package service.dishComparators;

import Model.Dish;
import Model.Product;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortByCaloriesTest {
    @Test
    void SortTest() {
        // Arrange
        ArrayList<Dish> dishes = new ArrayList<>();
        ArrayList<Product> Products = new ArrayList<>();
        dishes.add(new Dish("0",1,1,Products,null));
        dishes.add(new Dish("1",0,0,Products,null));
        dishes.add(new Dish("2",2,2,Products,null));
        dishes.add(new Dish("3",4,10,Products,null));
        dishes.add(new Dish("4",3,2,Products,null));
        dishes.add(new Dish("5",5,76,Products,null));
        boolean isProperSorted = true;

        // Act
        Collections.sort(dishes, new SortByCalories());
        for (int i = 0; i < dishes.size(); i++) {
            //weight is used to indicate proper sort order in test
            if (dishes.get(i).getDishWeight() != i){
                isProperSorted = false;
                break;
            }
        }

        // Assert
        assertTrue(isProperSorted);
    }
}
