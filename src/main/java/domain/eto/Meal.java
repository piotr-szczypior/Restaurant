package domain.eto;

import java.util.List;

import domain.DietType;

public class Meal {

  //HINT: A good practice is to have private fields in class and accessor methods (getters / setters) to them
  private String name;
  private DietType dietType;
  private List<Produce> products;
  private int calories;
  private int price;

  public String getName() {

    return this.name;
  }

  public DietType getDietType() {

    return this.dietType;
  }

  public List<Produce> getProducts() {

    return this.products;
  }

  public int getCalories() {

    return this.calories;
  }

  public int getPrice() {

    return this.price;
  }

  public void setName(String name) {

    this.name = name;
  }

  public void setDietType(DietType dietType) {

    this.dietType = dietType;
  }

  public void setProducts(List<Produce> products) {

    this.products = products;
  }

  public void setCalories(int calories) {

    this.calories = calories;
  }

  public void setPrice(int price) {

    this.price = price;
  }

  //HINT: Its a good practice to overwrite the equals and hashcode methods, also generating tostrings

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Meal meal = (Meal) o;

    if (this.calories != meal.calories) {
      return false;
    }
    if (this.price != meal.price) {
      return false;
    }
    if (this.name != null ? !this.name.equals(meal.name) : meal.name != null) {
      return false;
    }
    if (this.dietType != meal.dietType) {
      return false;
    }
    return this.products != null ? this.products.equals(meal.products) : meal.products == null;
  }

  @Override
  public int hashCode() {

    int result = this.name != null ? this.name.hashCode() : 0;
    result = 31 * result + (this.dietType != null ? this.dietType.hashCode() : 0);
    result = 31 * result + (this.products != null ? this.products.hashCode() : 0);
    result = 31 * result + this.calories;
    result = 31 * result + this.price;
    return result;
  }

  @Override
  public String toString() {

    return "Meal{" +
        "name='" + name + '\'' +
        ", dietType=" + dietType +
        ", products=" + products +
        ", calories=" + calories +
        ", price=" + price +
        '}';
  }
}
