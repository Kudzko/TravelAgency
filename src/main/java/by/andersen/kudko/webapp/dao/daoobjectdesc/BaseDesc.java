package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.dao.AbstractDAO;
import by.andersen.kudko.webapp.dao.FactoryDAO;
import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Entity;

import java.sql.Connection;

public class BaseDesc {

    public BaseDesc() {
    }

    public static Entity getEntityById(Class entityClass, Connection connection, Integer id) throws DAOException {
        Entity entity;
        FactoryDAO factoryDAO = FactoryDAO.getInstance();
        AbstractDAO dao = factoryDAO.getDAO(entityClass);
        dao.setConnection(connection);
        entity = dao.getByPK(id);
        dao.releaseConnectionFromDAO();
        return entity;
    }
}
