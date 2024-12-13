package service.dishComparators;

import Model.Dish;

import java.util.Comparator;

public class SortByWeight implements Comparator<Dish> {

    //to use, run "Collections.sort(list of dishes, new SortByWeight());"
    //will then sort that array given as input
    public int compare(Dish d1, Dish d2){
        if(d1.getDishWeight() - d2.getDishWeight() > 0){
            return 1;
        }
        else if(d1.getDishWeight() - d2.getDishWeight() < 0){
            return -1;
        }
        return 0;
    }
}
