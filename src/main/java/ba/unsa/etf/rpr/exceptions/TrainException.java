package ba.unsa.etf.rpr.exceptions;

/**
 * TrainException is a custom exception class for handling errors related to train operations.
 * It extends the base Exception class and provides two constructors for creating instances of the exception.
 * @author Tajra Selimovic
 */
public class TrainException extends Exception{
    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }
}
