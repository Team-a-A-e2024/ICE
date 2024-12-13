package service.dishComparators;

import Model.Dish;
import Model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortByWeightTest {
    @Test
    void SortTest() {
        // Arrange
        ArrayList<Dish> dishes = new ArrayList<>();
        ArrayList<Product> Products = new ArrayList<>();
        dishes.add(new Dish("0",1,1,Products));
        dishes.add(new Dish("1",0,0,Products));
        dishes.add(new Dish("2",2,2,Products));
        dishes.add(new Dish("3",10,4,Products));
        dishes.add(new Dish("4",2,3,Products));
        dishes.add(new Dish("5",76,5,Products));
        boolean isProperSorted = true;

        // Act
        Collections.sort(dishes, new SortByWeight());
        for (int i = 0; i < dishes.size(); i++) {
            //calories is used to indicate proper sort order
            if (dishes.get(i).getDishCalories() != i){
                isProperSorted = false;
            }
        }
        // Assert
        assertTrue(isProperSorted);
    }
}
