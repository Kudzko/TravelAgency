package by.andersen.kudko.dao.daointerface;

import by.andersen.kudko.dao.AbstractDAO;

public interface IFactoryDAO {
    interface DAOCreator{
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
