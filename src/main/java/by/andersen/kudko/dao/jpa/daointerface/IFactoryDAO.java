package by.andersen.kudko.dao.jpa.daointerface;

import by.andersen.kudko.dao.jpa.daoimpl.AbstractDAO;

public interface IFactoryDAO {
    interface DAOCreator {
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
