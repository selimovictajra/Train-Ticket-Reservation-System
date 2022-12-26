package ba.unsa.etf.rpr.exceptions;

public class TrainException extends Exception{
    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }
}
