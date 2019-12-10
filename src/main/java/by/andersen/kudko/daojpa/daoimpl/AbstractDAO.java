package by.andersen.kudko.daojpa.daoimpl;

import by.andersen.kudko.daojpa.daointerface.IAbstractDAO;
import by.andersen.kudko.daojpa.exception.DAOException;
import by.andersen.kudko.jpaentity.BEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO<E extends BEntity, PK extends Integer> implements IAbstractDAO<E, PK> {

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public AbstractDAO() {
        init("Hibernate_JPA");
    }

    protected abstract E findEntity(EntityManager entityManager, Integer pk);


    @Override
    public E create(E entity) throws DAOException {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void update(E entity) throws DAOException {
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(E entity) throws DAOException {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public E getById(PK pk) throws DAOException {
        E result = findEntity(entityManager, pk);
        return result;
    }

    /**
     * Initialize EntityManagerFactory and EntityManager
     */
    public void init(String persisntenceUnitName) {
        entityManagerFactory = Persistence.createEntityManagerFactory(persisntenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Closes EntityManagerFactory and EntityManager
     */
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
