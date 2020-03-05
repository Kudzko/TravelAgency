package by.andersen.kudko.daojpa.daointerface;

import by.andersen.kudko.daojpa.exception.DAOException;
import by.andersen.kudko.model.jpaentity.BEntity;

public interface IAbstractDAO<E extends BEntity,
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
    void update(E entity) throws DAOException;

    /**
     * delete record from DB
     *
     * @param entity
     * @return
     */
    void delete(E entity) throws DAOException;

    /**
     * Returns entity by id (PK) from DB
     *
     * @param pk
     * @return
     */
    E getById(PK pk) throws DAOException;
}


