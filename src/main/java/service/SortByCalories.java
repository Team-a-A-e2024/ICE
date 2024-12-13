package service;

import Model.Dish;
import java.util.Comparator;

public class SortByCalories implements Comparator<Dish> {

    //to use, run "Collections.sort(list of dishes, new SortByCalories());"
    //will then sort that array given as input
    public int compare(Dish d1, Dish d2){
        return d1.getDishCalories() - d2.getDishCalories();
    }
}
