package Models.enums;

public enum DishCategory {
    BREAKFAST,
    LUNCH,
    DINNER;

    @Override public String toString(){
        return name().toLowerCase();
    }
}
