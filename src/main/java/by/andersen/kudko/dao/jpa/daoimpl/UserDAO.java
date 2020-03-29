package by.andersen.kudko.dao.jpa.daoimpl;


import by.andersen.kudko.dao.jpa.daointerface.IUserDAO;
import by.andersen.kudko.model.jpaentity.User;
import by.andersen.kudko.model.jpaentity.User_;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class UserDAO extends AbstractDAO<User, Integer> implements IUserDAO {
    @Override
    protected User findEntity(EntityManager entityManager, Integer pk) {
        return entityManager.find(User.class, pk);
    }


    @Override
    public List<User> findByName(String name) {
        Query query = entityManager.createNamedQuery("find user by name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<User> findBySurname(String surname) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);
        userCriteria.select(userRoot);
        userCriteria.where(criteriaBuilder.equal(userRoot.get(User_.surname), surname));
        TypedQuery<User> typedQuery = entityManager.createQuery(userCriteria);
        List<User> resultList = typedQuery.getResultList();

        return resultList;
    }
}
