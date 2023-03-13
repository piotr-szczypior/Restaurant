package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.exception.NoFoodFoundException;

/**
 * Hint: To create a test class use intellij ->  go to a class, press alt+enter and use "create test" option.
 * Alternatively use alt + insert (einfugen) combination, and select "Test" use Alt
 */
class MenuExampleTest {

  MenuExample menuExample = new MenuExample();

  MenuServiceImpl service = new MenuServiceImpl();

  /**
   * Hint: Tests compromises itself on 3 blocks: given when then. Given is a block where you create your test data.
   * When is a block where you test your method. Then is a block where you assert (check) your code results.
   */
  @Test
  public void shouldFindVegetarianFood() {

    // given
    List<Meal> meals = new ArrayList<>();
    meals.add(createVegetarianMeal());
    meals.add(createRegularMeal());
    // when
    List<Meal> result = menuExample.findVegetarianFood(meals);
    // then
    int expectedSize = 1;
    //HINT: write what result you expect after the method was called
    Assertions.assertEquals(expectedSize, result.size());
    Assertions.assertEquals("Salad", result.get(0).getName());
  }

  @Test
  public void shouldThrowExceptionWhenInputDoesNotContainVegetarian() {

    // given
    List<Meal> meals = new ArrayList<>();
    meals.add(createRegularMeal());
    meals.add(createRegularMeal());
    // when
    // then
    //HINT: When checking for exceptions, when and then are usually written in one method call
    Assertions.assertThrows(NoFoodFoundException.class, ()->
        menuExample.findVegetarianFood(meals));
  }

  @Test
  public void shouldThrowExceptionWhenInputWasEmpty() {

    // given
    List<Meal> meals = new ArrayList<>();
    // when
    // then
    //HINT: When checking for exceptions, when and then are usually written in one method call
    Assertions.assertThrows(NoFoodFoundException.class, ()->
        menuExample.findVegetarianFood(meals));


  }

  @Test
  public void shouldProperlyHandleNullAsInput() {

    // given
    List<Meal> meals = null;
    // when
    // then
    //HINT: When checking for exceptions, when and then are usually written in one method call
    Assertions.assertThrows(IllegalArgumentException.class, ()->
        menuExample.findVegetarianFood(meals));
  }

  //HINT: methods that you use to create data for tests should be on the bottom of the class
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