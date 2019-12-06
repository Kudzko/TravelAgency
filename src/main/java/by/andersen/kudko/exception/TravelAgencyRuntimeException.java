package by.andersen.kudko.exception;

public class TravelAgencyRuntimeException extends RuntimeException {
    public TravelAgencyRuntimeException() {
    }

    public TravelAgencyRuntimeException(String message) {
        super(message);
    }

    public TravelAgencyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyRuntimeException(Throwable cause) {
        super(cause);
    }

    public TravelAgencyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
