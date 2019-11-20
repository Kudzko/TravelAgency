package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.exception.DAOException;

public interface IFactoryDAO {
    interface DAOCreator{
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
