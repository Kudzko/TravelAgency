package by.andersen.kudko.exception;

public class TravelAgencyException extends Exception {
    public TravelAgencyException() {
    }

    public TravelAgencyException(String message) {
        super(message);
    }

    public TravelAgencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyException(Throwable cause) {
        super(cause);
    }

    public TravelAgencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
