package by.andersen.kudko.dao.connection;

import by.andersen.kudko.exception.TechnicalException;

public class ConnectionPoolException extends TechnicalException {
    public ConnectionPoolException(String message, Exception e){
        super(message,e);
    }
}
