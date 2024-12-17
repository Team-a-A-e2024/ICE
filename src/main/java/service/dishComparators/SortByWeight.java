package service.dishComparators;

import Models.Dish;

import java.util.Comparator;

public class SortByWeight implements Comparator<Dish> {

    //to use, run "Collections.sort(list of dishes, new SortByWeight());"
    //will then sort that array given as input
    public int compare(Dish d1, Dish d2){
        return (int) (d1.getDishWeight() - d2.getDishWeight());
    }
}
