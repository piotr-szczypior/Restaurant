package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        if (meals == null) {
            throw new IllegalArgumentException();
        }

        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> DietType.getVegetarian().contains(meal.getDietType()))
                .collect(Collectors.toList());
        if (filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }

        return filteredMeals;
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        if (meals == null) {
            throw new IllegalArgumentException();
        }

        if (diet == null) {
            return meals;
        }

        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> meal.getDietType().equals(diet))
                .collect(Collectors.toList());

        if (filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }
        return filteredMeals;
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price) {

        if (meals == null || price == null) {
            throw new IllegalArgumentException();
        }

        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> meal.getPrice() <= price)
                .collect(Collectors.toList());

        if (filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }
        return filteredMeals;
    }

    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        if (meals == null || minCalories == null || maxCalories == null) {
            throw new IllegalArgumentException();
        }

        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> meal.getCalories() >= minCalories && meal.getCalories() <= maxCalories)
                .collect(Collectors.toList());
        if (filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }
        return filteredMeals;
    }

    @Override
    public List<Meal> findFoodWithGivenName(List<Meal> meals, String name) {

        if (meals == null) {
            throw new IllegalArgumentException();
        }
        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> meal.getName().startsWith(name))
                .collect(Collectors.toList());
        if(filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }

        return filteredMeals;
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce produce) {

        if (meals == null || produce == null) {
            throw new IllegalArgumentException();
        }

        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> meal.getProducts().contains(produce))
                .collect(Collectors.toList());

        if (filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }
        return filteredMeals;
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        if (meals == null) {
            throw new IllegalArgumentException();
        }

        if (products == null) {
            return meals;
        }
        List<Meal> filteredMeals = meals.parallelStream()
                .filter(meal -> meal.getProducts().stream()
                        .noneMatch(products::contains))
                .collect(Collectors.toList());

        if (filteredMeals.isEmpty()) {
            throw new NoFoodFoundException();
        }
        return filteredMeals;
    }
}
