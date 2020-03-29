package by.andersen.kudko.dao.jdbctemplate.daointerface;


import by.andersen.kudko.dao.jdbctemplate.jdbctemplateimpl.AbstractDAO;

public interface IFactoryDAO {
    interface DAOCreator {
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class entityClass);
}
