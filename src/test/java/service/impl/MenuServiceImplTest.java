package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceImplTest {

    private final MenuService menuService = new MenuServiceImpl();

    @Test
    void shouldFindVegetarianFood() {
        // given
        List<Meal> meals = new ArrayList<>();
        meals.add(createVegetarianMeal());
        meals.add(createRegularMeal());

        //when
        List<Meal> result = menuService.findVegetarianFood(meals);

        //then
        int expectedSize = 1;
        assertEquals(expectedSize, result.size());
        Assertions.assertTrue(DietType.getVegetarian().contains(result.get(0).getDietType()));
        assertEquals("Salad", result.get(0).getName());
    }

    @Test
    void findVegetarianFood_shouldThrowExceptionWhenInputIsEmpty() {
        // given
        List<Meal> meals = new ArrayList<>();

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findVegetarianFood(meals));
    }

    @Test
    void findVegetarianFood_shouldProperlyHandleNull() {
        // given
        List<Meal> meals = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> menuService.findVegetarianFood(meals));
    }

    @Test
    void findVegetarianFood_shouldThrowExceptionWhenVegetarianWasNotFound() {
        // given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findVegetarianFood(meals));
    }


    @Test
    void shouldFindFoodByType() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createVegetarianMeal());
        meals.add(createRegularMeal());
        DietType dietType = DietType.REGULAR;

        //when
        List<Meal> result = menuService.findFoodByType(meals, dietType);

        //then
        int expectedSize = 1;
        Assertions.assertEquals(dietType, result.get(0).getDietType());
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Scrambled Eggs", result.get(0).getName());
    }

    @Test
    void findFoodByType_shouldThrowExceptionWhenInputIsEmpty() {
        // given
        List<Meal> meals = new ArrayList<>();

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findVegetarianFood(meals));
    }

    @Test
    void findFoodByType_shouldProperlyHandleNull() {
        // given
        List<Meal> meals = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> menuService.findVegetarianFood(meals));
    }

    @Test
    void findFoodByType_ShouldThrowExceptionWhenFoodWasNotFound() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());
        DietType dietType = DietType.VEGETARIAN;

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodByType(meals, dietType));

    }


    @Test
    void findFoodCheaperThanPrice() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createVegetarianMeal());
        int price = 12;

        //when
        List<Meal> result = menuService.findFoodCheaperThanPrice(meals, price);

        //then
        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Scrambled Eggs", result.get(0).getName());
        Assertions.assertTrue(result.get(0).getPrice() <= price);
    }

    @Test
    void findFoodCheaperThanPrice_ShouldHandleNullAsInput() {
        //given
        List<Meal> meals = null;
        Integer price = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> menuService.findFoodCheaperThanPrice(meals, price));
    }

    @Test
    void findFoodCheaperThanPrice_ShouldThrowExceptionWhenInputIsEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        int price = 9;

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodCheaperThanPrice(meals, price));
    }

    @Test
    void findFoodCheaperThanPrice_ShouldThrowExceptionWhenFoodWasNotFound() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());
        int price = 9;

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodCheaperThanPrice(meals, price));
    }

    @Test
    void shouldFindFoodCheaperWithCalories() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createVegetarianMeal());
        int minCalories = 0;
        int maxCalories = 260;

        //when
        List<Meal> result = menuService.findFoodCheaperWithCalories(meals, minCalories, maxCalories);

        //then
        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
        Assertions.assertTrue(result.get(0).getCalories() <= maxCalories);
    }

    @Test
    void findFoodCheaperWithCalories_ShouldThrowExceptionWhenFoodWasNotFound() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());
        int minCalories = 100;
        int maxCalories = 240;

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodCheaperWithCalories(meals, minCalories, maxCalories));
    }

    @Test
    void findFoodCheaperWithCalories_ShouldHandleNull() {
        //given
        List<Meal> meals = null;
        Integer minCalories = null;
        Integer maxCalories = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> menuService.findFoodCheaperWithCalories(meals, minCalories, maxCalories));
    }

    @Test
    void findFoodCheaperWithCalories_ShouldThrowExceptionWhenInputIsEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        int minCalories = 100;
        int maxCalories = 240;

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodCheaperWithCalories(meals, minCalories, maxCalories));
    }


    @Test
    void shouldFindFoodContaining() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createVegetarianMeal());
        Produce butter = new Produce();
        butter.setName("Oil");
        butter.setProductType(ProductType.DAIRY);

        //when
        List<Meal> result = menuService.findFoodContaining(meals, butter);

        //then
        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        assertTrue(result.get(0).getProducts().contains(butter));
        assertEquals("Scrambled Eggs", result.get(0).getName());
    }

    @Test
    void findFoodContaining_shouldHandleNullAsInput() {
        //given
        List<Meal> meals = null;
        Produce produce = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> menuService.findFoodContaining(meals, produce));
    }

    @Test
    void findFoodContaining_ShouldThrowExceptionWhenNoFoodWasFound() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());
        Produce lettuce = new Produce();
        lettuce.setName("Lettuce");
        lettuce.setProductType(ProductType.VEGETABLE);

        //then
        assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodContaining(meals, lettuce));
    }

    @Test
    void findFoodContaining_ShouldThrowExceptionWhenInputIsEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        Produce lettuce = new Produce();
        lettuce.setName("Lettuce");
        lettuce.setProductType(ProductType.VEGETABLE);

        //then
        assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodContaining(meals, lettuce));
    }

    @Test
    void shouldFindFoodExcludingAll() {
        //given
        List<Meal> meals = new ArrayList<>(
                List.of(createRegularMeal(), createVegetarianMeal())
        );
        Produce eggs = createEggs();
        Produce butter = createButter();
        List<Produce> produces = new ArrayList<>(List.of(eggs, butter));

        //when
        List<Meal> result = menuService.findFoodExcludingAll(meals, produces);

        //then
        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
        assertFalse(result.get(0).getProducts().containsAll(produces));
    }

    @Test
    void findFoodExcludingAll_ShouldHandleNullAsExcludingParameter() {
        //given
        List<Meal> meals = new ArrayList<>(
                List.of(createRegularMeal(), createVegetarianMeal())
        );
        List<Produce> produces = null;

        //when
        List<Meal> result = menuService.findFoodExcludingAll(meals, produces);

        //then
        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    void findFoodExcludingAll_ShouldHandleNull() {
        //given
        List<Meal> meals = null;
        List<Produce> produces = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> menuService.findFoodExcludingAll(meals, produces));
    }

    @Test
    void findFoodExcludingAll_ShouldThrowExceptionWhenNoFoodWasFound() {
        //given
        List<Meal> meals = new ArrayList<>(
                List.of(createRegularMeal(), createRegularMeal())
        );
        Produce eggs = createEggs();
        Produce butter = createButter();
        List<Produce> produces = new ArrayList<>(List.of(eggs, butter));
        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodExcludingAll(meals, produces));
    }

    @Test
    void findFoodExcludingAll_ShouldThrowExceptionWhenInputIsEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        Produce eggs = createEggs();
        Produce butter = createButter();
        List<Produce> produces = new ArrayList<>(List.of(eggs, butter));

        //then
        Assertions.assertThrows(NoFoodFoundException.class,
                () -> menuService.findFoodExcludingAll(meals, produces));
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