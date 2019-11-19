package by.andersen.kudko.webapp.dao.daoint;

import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Entity;

public interface IAbstractDAO<E extends Entity,
        PK extends Integer> {

    /**
     * Create record in DB
     *
     * @param entity
     * @return entity with id which matches with DB
     */
    E create(E entity) throws DAOException;


    /**
     * Update record in DB
     *
     * @param entity
     * @return
     */
    int update(E entity) throws DAOException;

    /**
     * delete record from DB
     *
     * @param entity
     * @return
     */
    int delete(E entity) throws DAOException;

    /**
     * Returns entity by id (PK) from DB
     *
     * @param pk
     * @return
     */
    E getByPK(PK pk) throws DAOException;
}


