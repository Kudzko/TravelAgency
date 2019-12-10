package by.andersen.kudko.daojpa.daoimpl;

import by.andersen.kudko.daojpa.daointerface.IUserDAO;
import by.andersen.kudko.jpaentity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class UserDAO extends AbstractDAO<User, Integer> implements IUserDAO {
    @Override
    protected User findEntity(EntityManager entityManager, Integer pk) {
        return entityManager.find(User.class, pk);
    }


    @Override
    public List<User> findByName(String name) {
        List<User> users;
        Query query = entityManager.createNamedQuery("find user by name");
        query.setParameter("name", name);
        users = query.getResultList();
        return users;
    }
}
