package by.andersen.kudko.webapp.dao.connection;

import by.andersen.kudko.webapp.exception.TechnicalException;

public class ConnectionPoolException extends TechnicalException {
    public ConnectionPoolException(String message, Exception e){
        super(message,e);
    }
}
