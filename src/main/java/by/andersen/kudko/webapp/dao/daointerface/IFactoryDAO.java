package by.andersen.kudko.webapp.dao.daointerface;

import by.andersen.kudko.webapp.dao.AbstractDAO;

public interface IFactoryDAO {
    interface DAOCreator{
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
