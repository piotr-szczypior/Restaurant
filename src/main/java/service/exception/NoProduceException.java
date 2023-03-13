package service.exception;

public class NoProduceException extends RuntimeException {
    @Override
    public String getMessage() {
        return "There is no such produce in storage";
    }

    public NoProduceException(String message) {
        super(message);
    }
}
