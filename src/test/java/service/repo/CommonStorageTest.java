package service.repo;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CommonStorageTest {


    @BeforeEach
    void clearStorage() {
        CommonStorage.clearStorage();
    }

    @Test
    void shouldAddToStorage() {
        //given
        Produce produce = createButter();
        Integer amount = 10;

        //when
        CommonStorage.addToStorage(produce, amount);

        //then
        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, CommonStorage.getProducePerAmount().size());
        Assertions.assertEquals(amount, CommonStorage.getProducePerAmount().get(produce));
    }

    @Test
    void shouldAddToStorage_whenStorageIsNotEmpty() {
        //given
        Produce produce = createButter();
        int amount = 10;
        CommonStorage.addToStorage(produce, amount);
        amount = 20;

        //when
        CommonStorage.addToStorage(produce, amount);

        //then
        int expectedSize = 1;
        int expectedAmount = 30;
        Assertions.assertEquals(expectedSize, CommonStorage.getProducePerAmount().size());
        Assertions.assertEquals(expectedAmount, CommonStorage.getProducePerAmount().get(produce));
    }

    @Test
    void removeFromStorage() {
        Produce produce = createButter();
        Integer amount = 10;
        CommonStorage.addToStorage(produce, amount);

        //when
        CommonStorage.removeFromStorage(produce, 5);

        //then
        int expectedSize = 1;
        int expectedAmount = 5;
        Assertions.assertEquals(expectedSize, CommonStorage.getProducePerAmount().size());
        Assertions.assertEquals(expectedAmount, CommonStorage.getProducePerAmount().get(produce));
    }

    @Test
    void removeFromStorage_removeAmountBiggerThanQuantity() {
        Produce produce = createButter();
        Integer amount = 10;
        CommonStorage.addToStorage(produce, amount);

        //when
        CommonStorage.removeFromStorage(produce, 100);

        //then
        int expectedSize = 1;
        int expectedAmount = 0;
        Assertions.assertEquals(expectedSize, CommonStorage.getProducePerAmount().size());
        Assertions.assertEquals(expectedAmount, CommonStorage.getProducePerAmount().get(produce));
    }

    @Test
    void removeFromStorage_shouldNotRemoveProduce_EmptyStorage() {
        Produce produce = createButter();
        Integer quantityToBeRemoved = 100;

        //when
        CommonStorage.removeFromStorage(produce, quantityToBeRemoved);

        //then
        int expectedSize = 0;
        Assertions.assertEquals(expectedSize, CommonStorage.getProducePerAmount().size());
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