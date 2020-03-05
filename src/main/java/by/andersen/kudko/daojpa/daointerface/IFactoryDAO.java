package by.andersen.kudko.daojpa.daointerface;

import by.andersen.kudko.daojpa.daoimpl.AbstractDAO;

public interface IFactoryDAO {
    interface DAOCreator {
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
