package by.andersen.kudko.dao.springData.daointerface;

import by.andersen.kudko.dao.simple.AbstractDAO;

public interface IFactoryDAO {
    interface DAOCreator {
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
