package service;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.api.MenuService;
import service.api.StorageService;
import service.exception.NoProduceException;
import service.impl.MenuServiceStorageImpl;
import service.repo.CommonStorage;

import java.util.ArrayList;
import java.util.List;

class MenuServiceStorageImplTest {

    MenuService menuServiceStorage = new MenuServiceStorageImpl();

    @BeforeEach
    public void clearStorage() {
        CommonStorage.clearStorage();
    }

    @Test
    void findVegetarianFood_DoesNotTrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));

        //then
        Assertions.assertDoesNotThrow(() -> menuServiceStorage.findVegetarianFood(meals));
    }

    @Test
    void findVegetarianFood_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findVegetarianFood(meals));
    }

    @Test
    void findFoodByType_ShouldNotThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        DietType dietType = DietType.VEGETARIAN;

        //then
        Assertions.assertDoesNotThrow(() -> menuServiceStorage.findFoodByType(meals, dietType));

    }

    @Test
    void findFoodByType_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        DietType dietType = DietType.VEGETARIAN;

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findFoodByType(meals, dietType));
    }

    @Test
    void findFoodWithGivenName_ShouldNotThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        String name = "Salad";

        //then
        Assertions.assertDoesNotThrow(() -> menuServiceStorage.findFoodWithGivenName(meals, name));
    }

    @Test
    void findFoodWithGivenName_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        String name = "Salad";

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findFoodWithGivenName(meals, name));
    }

    @Test
    void findFoodCheaperWithCalories_ShouldNotThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        Integer minCalories = 0;
        Integer maxCalories = 300;

        //then
        Assertions.assertDoesNotThrow(
                () -> menuServiceStorage.findFoodCheaperWithCalories(meals, minCalories, maxCalories)
        );
    }

    @Test
    void findFoodCheaperWithCalories_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        Integer minCalories = 0;
        Integer maxCalories = 300;

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findFoodCheaperWithCalories(meals, minCalories, maxCalories)
        );
    }

    @Test
    void findFoodCheaperThanPrice_ShouldNotThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        Integer price = 100;

        //then
        Assertions.assertDoesNotThrow(
                () -> menuServiceStorage.findFoodCheaperThanPrice(meals, price)
        );
    }

    @Test
    void testFindFoodWithGivenName_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        Integer price = 100;

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findFoodCheaperThanPrice(meals, price)
        );
    }

    @Test
    void findFoodContaining_ShouldNotThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        Produce lettuce = createVegetable("Lettuce");

        //then
        Assertions.assertDoesNotThrow(
                () -> menuServiceStorage.findFoodContaining(meals, lettuce)
        );
    }

    @Test
    void findFoodContaining_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        Produce lettuce = createVegetable("Lettuce");

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findFoodContaining(meals, lettuce)
        );
    }

    @Test
    void findFoodExcludingAll_ShouldNotThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        List<Produce> excludingProduces = new ArrayList<>(List.of(createButter(), createEggs()));

        //then
        Assertions.assertDoesNotThrow(
                () -> menuServiceStorage.findFoodExcludingAll(meals, excludingProduces)
        );
    }

    @Test
    void findFoodExcludingAll_ShouldThrowException() {
        //given
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 0);
        List<Meal> meals = new ArrayList<>(List.of(createVegetarianMeal()));
        List<Produce> excludingProduces = new ArrayList<>(List.of(createButter(), createEggs()));

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> menuServiceStorage.findFoodExcludingAll(meals, excludingProduces)
        );
    }

    private Meal createRegularMeal() {

        Meal meal = new Meal();
        meal.setName("Scrambled Eggs");
        meal.setCalories(300);
        meal.setDietType(DietType.REGULAR);
        meal.setPrice(10);

        List<Produce> products = new ArrayList<>();
        products.add(createButter());
        products.add(createEggs());
        meal.setProducts(products);
        return meal;
    }

    private Produce createButter() {

        Produce butter = new Produce();
        butter.setName("Oil");
        butter.setProductType(ProductType.DAIRY);
        return butter;
    }

    private Produce createEggs() {

        Produce eggs = new Produce();
        eggs.setName("Chicken Eggs");
        eggs.setProductType(ProductType.EGGS);
        return eggs;
    }

    private Meal createVegetarianMeal() {

        Meal meal = new Meal();
        meal.setName("Salad");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGETARIAN);
        meal.setPrice(25);

        List<Produce> products = new ArrayList<>();
        products.add(createVegetable("Lettuce"));
        products.add(createVegetable("Tomato"));
        products.add(createVegetable("Cucumber"));
        meal.setProducts(products);
        return meal;
    }

    private Produce createVegetable(String vegetableName) {

        Produce veg = new Produce();
        veg.setName(vegetableName);
        veg.setProductType(ProductType.VEGETABLE);
        return veg;
    }
}