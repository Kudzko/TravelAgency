package by.andersen.kudko.daojpa.daoimpl;


import by.andersen.kudko.daojpa.daointerface.IFactoryDAO;
import by.andersen.kudko.model.jpaentity.*;

import java.util.HashMap;
import java.util.Map;

public class FactoryDAO implements IFactoryDAO {

    private static final FactoryDAO instance = new FactoryDAO();
    private Map<Class, DAOCreator> daoObjects;

    private FactoryDAO() {
        this.daoObjects = new HashMap<>();
        daoObjects.put(User.class, new DAOCreator() {
            @Override
            public AbstractDAO create() {
                return new UserDAO();
            }
        });
//        daoObjects.put(Country.class, new DAOCreator() {
//            @Override
//            public AbstractDAO create() {
//                return new CountryDAO();
//            }
//        });
//        daoObjects.put(Hotel.class, new DAOCreator() {
//            @Override
//            public AbstractDAO create() {
//                return new HotelDAO();
//            }
//        });
//        daoObjects.put(Tour.class, new DAOCreator() {
//            @Override
//            public AbstractDAO create() {
//                return new TourDAO();
//            }
//        });
        daoObjects.put(Order.class, new DAOCreator() {
            @Override
            public AbstractDAO create() {
                return new OrderDAO();
            }
        });
    }

    public static FactoryDAO getInstance() {
        return instance;
    }

    /**
     * daoObjects.put(Order.class, new DAOCreator() {
     *
     * @param clazz
     * @param dao
     */
    public void addDAO(Class clazz, DAOCreator dao) {
        daoObjects.put(clazz, dao);
    }

    @Override
    public AbstractDAO getDAO(Class entityClass) {
        AbstractDAO dao = null;
        DAOCreator daoObject = daoObjects.get(entityClass);
        if (daoObject != null) {
            dao = daoObject.create();
        }
        return dao;
    }

}
