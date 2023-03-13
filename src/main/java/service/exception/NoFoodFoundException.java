package service.exception;

public class NoFoodFoundException extends RuntimeException {

  @Override
  public String getMessage() {

    return "Food for given criteria was not found";
  }
}
