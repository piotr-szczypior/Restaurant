package domain;

import java.util.Arrays;
import java.util.List;

public enum DietType {

    VEGETARIAN,
    REGULAR,
    VEGAN;

    public static List<DietType> getVegetarian() {
        return Arrays.asList(VEGETARIAN, VEGAN);
    }


}
