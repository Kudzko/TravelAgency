package by.andersen.kudko.webapp.dao.daointerface;

import by.andersen.kudko.webapp.dao.AbstractDAO;
import by.andersen.kudko.webapp.dao.exception.DAOException;

public interface IFactoryDAO {
    interface DAOCreator{
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
