package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.exception.NoProduceException;
import service.repo.CommonStorage;

import java.util.List;

public class MenuServiceStorageImpl extends MenuServiceImpl {

    private static void canMealBePreparedFromProductsInStorage(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException();
        }

        for (Produce produce : meal.getProducts()) {
            if (!CommonStorage.checkInStorage(produce)) {
                throw new NoProduceException("There is no " + produce.getName() + " in storage");
            }
        }
    }

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        List<Meal> vegetarianFood = super.findVegetarianFood(meals);
        vegetarianFood.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return vegetarianFood;
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        List<Meal> foodByType = super.findFoodByType(meals, diet);
        foodByType.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return foodByType;
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price) {
        List<Meal> foodCheaperThanPrice = super.findFoodCheaperThanPrice(meals, price);
        foodCheaperThanPrice.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return foodCheaperThanPrice;
    }

    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        List<Meal> foodCheaperWithCalories = super.findFoodCheaperWithCalories(meals, minCalories, maxCalories);
        foodCheaperWithCalories.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return foodCheaperWithCalories;
    }

    @Override
    public List<Meal> findFoodWithGivenName(List<Meal> meals, String name) {
        List<Meal> foodCheaperThanPrice = super.findFoodWithGivenName(meals, name);
        foodCheaperThanPrice.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return foodCheaperThanPrice;
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce produce) {
        List<Meal> foodContaining = super.findFoodContaining(meals, produce);
        foodContaining.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return foodContaining;
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        List<Meal> foodExcludingAll = super.findFoodExcludingAll(meals, products);
        foodExcludingAll.forEach(MenuServiceStorageImpl::canMealBePreparedFromProductsInStorage);
        return foodExcludingAll;
    }
}
