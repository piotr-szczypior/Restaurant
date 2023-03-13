package service.api;

import java.util.List;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;

public interface MenuService {

  /**
   * For a given List of meals, find all meals that are of dietType = {@link DietType#VEGETARIAN}.
   * Hint: You can copy the implementation from MenuExample
   * @param meals
   * @return list of found meals
   */
  List<Meal> findVegetarianFood(List<Meal> meals);


  /**
   * For a given List of meals, find all meals that are of certain dietType
   * @param meals
   * @param diet
   * @return list of found meals
   */
  List<Meal> findFoodByType(List<Meal> meals, DietType diet);

  /**
   * For a given List of meals return meals that cost less than given parameter.
   * @param meals
   * @param price
   * @return list of found meals
   */
  List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price);

  /**
   * For a given List of meals return meals that have calorie intake between two values
   * @param meals
   * @return list of found meals
   */
  List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories);

  /**
   * For a given List of meals return meals that name starts with a String. This method ignores character case when
   * searching.
   * @param meals
   * @param name - can be a partial String
   * @return list of found meals
   */
  List<Meal> findFoodWithGivenName(List<Meal> meals, String name);


  /**
   * For a given List of meals return meals that does contains certain product
   * @param meals
   * @param product product that is in the meal
   * @return list of found meals
   */
  //HINT: meal.getProducts()
  List<Meal> findFoodContaining(List<Meal> meals, Produce product);

  /**
   * For a given List of meals return meals that does not contain any of the products
   * @param meals
   * @param products that should be excluded from the menu
   * @return list of found meals
   */
  List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products);


}
