package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.api.StorageService;
import service.exception.NoProduceException;
import service.repo.Storage;

import java.util.ArrayList;
import java.util.List;

class StorageServiceImplTest {

    StorageService storageService = new StorageServiceImpl();

    @Test
    void canMealBePreparedFromProductsInStorage_shouldNotThrowAnException() {
        //given
        Storage storage = new Storage();
        storage.addProduce(createVegetable("Tomato"));
        storage.addProduce(createVegetable("Tomato"));
        storage.addProduce(createVegetable("Lettuce"));
        storage.addProduce(createVegetable("Cucumber"));

        Meal salad = createMeal();

        //then
        Assertions.assertDoesNotThrow(
                () -> storageService.canMealBePreparedFromProductsInStorage(salad, storage)
        );
    }

    @Test
    void canMealBePreparedFromProductsInStorage_shouldThrowAnException() {
        Storage storage = new Storage();
        storage.addProduce(createVegetable("Potato"));

        Meal salad = createMeal();

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> storageService.canMealBePreparedFromProductsInStorage(salad, storage));
    }

    @Test
    void canMealBePreparedFromProductsInStorage_shouldHandleNull() {
        Storage storage = null;

        Meal salad = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> storageService.canMealBePreparedFromProductsInStorage(salad, storage));
    }

    @Test
    void canMealBePreparedFromProductsInStorage_shouldHandeEmptyInput() {
        Meal salad = createMeal();
        Storage storage = new Storage();
        storage.getProducePerAmount().put(createVegetable("Lettuce"), 0);

        //then
        Assertions.assertThrows(NoProduceException.class,
                () -> storageService.canMealBePreparedFromProductsInStorage(salad, storage));
    }

    private Meal createMeal() {

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