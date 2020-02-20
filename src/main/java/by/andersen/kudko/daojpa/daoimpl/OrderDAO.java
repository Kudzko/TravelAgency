package by.andersen.kudko.daojpa.daoimpl;

import by.andersen.kudko.jpaentity.BEntity;
import by.andersen.kudko.jpaentity.Order;
import by.andersen.kudko.jpaentity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;

public class OrderDAO extends AbstractDAO {
    @Override
    protected BEntity findEntity(EntityManager entityManager, Integer pk) {
        return entityManager.find(Order.class, pk);
    }

    public Date getMaxDate(){
        Query query = entityManager.createNamedQuery("order.maxDate");
        return (Date)  query.getSingleResult();
    }
}
