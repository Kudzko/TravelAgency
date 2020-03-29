package by.andersen.kudko.dao.jdbctemplate.daointerface;

import by.andersen.kudko.dao.jdbctemplate.exception.DAOException;
import by.andersen.kudko.model.entity.BEntity;


public interface IAbstractDAO<E extends BEntity,
        PK extends Integer> {

    /**
     * Returns entity by id (PK) from DB
     *
     * @param pk
     * @return
     */
    E getById(PK pk) throws DAOException;
}


