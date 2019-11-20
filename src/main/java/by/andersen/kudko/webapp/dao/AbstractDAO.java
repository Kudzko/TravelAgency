package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.daoint.IAbstractDAO;
import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Entity;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public abstract class AbstractDAO<E extends Entity, PK extends Integer> implements IAbstractDAO<E, PK> {
    protected Connection conn;
    private Lock lock;
    private FactoryDAO factoryDAO;

    public AbstractDAO() {
        this.lock = new ReentrantLock();
        this.factoryDAO = FactoryDAO.getInstance();
    }

    public abstract String createQuery();

    public abstract void createPrpStmt(E entity, PreparedStatement prpStmt) throws SQLException;

    public abstract String updateSql();

    public abstract void updatePrpStmt(E entity, PreparedStatement prpStmt) throws SQLException;

    public abstract String deleteSql();

    public abstract void deletePrpStmt(E entity, PreparedStatement prpStmt) throws SQLException;

    public abstract String getByPKSQL();

    public abstract E resultsetStringToObject(ResultSet resultSet, Connection connection) throws SQLException, DAOException;


    @Override
    public E create(E entity) throws DAOException {

        if (entity.getId() != 0) {
            throw new DAOException("Entity has already been added in DB");
        }
        saveDependencies(entity);

        PreparedStatement prpStmt = null;
        String sqlStmt = createQuery();

        try {
            conn.setAutoCommit(false);
            prpStmt = conn.prepareStatement(sqlStmt,
                    Statement.RETURN_GENERATED_KEYS);
            createPrpStmt(entity, prpStmt);


            //receives key of the executing query
            int count;
            count = prpStmt.executeUpdate();
            if (count != 1) {
                throw new DAOException("Created more than 1 record." +
                        " (Created " +
                        "" + count + "record(s)).");
            }

            ResultSet generatedKeys = prpStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                entity.setId(id);
            }
        } catch (SQLException e) {
            log.warn("database access error occurs", e);
            throw new DAOException("Can not create record into DB", e);
        } finally {
            try {
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                log.warn("Connection haven't switched on autocommit");
            }
            closeStatement(prpStmt);
        }
        return entity;
    }


    /**
     * @param entity
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or
     * (2) 0 for SQL statements that return nothing
     */
    @Override
    public int update(E entity) throws DAOException {
        int amountRows = 0;
        if (entity.getId() != 0) {
            updateDependencies(entity);
            String sql = updateSql();
            PreparedStatement prpStmt = null;
            try {
                conn.setAutoCommit(false);
                prpStmt = conn.prepareStatement(sql);
                updatePrpStmt(entity, prpStmt);
                amountRows = prpStmt.executeUpdate();

            } catch (SQLException e) {
                log.warn("database access error occurs during update method operating", e);
                throw new DAOException("database access error occurs during update method operating", e);
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    log.warn("Connection haven't switched on autocommit");
                }
                closeStatement(prpStmt);
            }
        }
        return amountRows;
    }

    @Override
    public int delete(E entity) throws DAOException {
        String sql = deleteSql();
        int amountChangedRows = 0;
        PreparedStatement prpStmt = null;
        try {
            conn.setAutoCommit(false);
            prpStmt = conn.prepareStatement(sql);
            deletePrpStmt(entity, prpStmt);
            prpStmt.executeUpdate();
        } catch (SQLException e) {
            String msg = "Something wrong with prepared statement in delete method";
            log.warn(msg);
            throw new DAOException(msg, e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                log.warn("Connection haven't switched on autocommit");
            }
            closeStatement(prpStmt);
        }
        return amountChangedRows;
    }

    @Override
    public E getByPK(PK pk) throws DAOException {
        String selectSQL = getByPKSQL();
        PreparedStatement prpStmt = null;
        ResultSet resultSet;
        List<E> objects;
        try {
            prpStmt = conn.prepareStatement(selectSQL);
            prpStmt.setInt(1, pk);
            resultSet = prpStmt.executeQuery();
            objects = resultsetToObjects(resultSet);

        } catch (SQLException e) {
            String msg = "Error in DB access occured";
            log.warn(msg);
            throw new DAOException(msg, e);
        } finally {
            closeStatement(prpStmt);
        }
        if (objects.size() > 1) {
            throw new DAOException("Nonunique PK. More then one entity returned");
        } else if (objects.size() == 1) {
            return objects.get(0);
        } else {
            return null;
        }

    }

    private List<E> resultsetToObjects(ResultSet resultSet) throws DAOException {
        List<E> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                E entity = resultsetStringToObject(resultSet, conn);
                entities.add(entity);
            }
        } catch (SQLException e) {
            String msg = "Error in DB access occured";
            log.warn(msg);
            throw new DAOException(msg, e);
        }
        return entities;
    }


    /**
     * Return link on connection which contains DAO
     *
     * @return
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Set connection into DAO object and locks DAO
     *
     * @return
     */
    public void setConnection(Connection connection) {
        this.conn = connection;
        lock.lock();
    }

    private void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.warn("Cann't close statement. DB access error occurs ", e);
        }

    }

    /**
     * extract connection from dao and release DAO
     *
     * @return
     */
    public Connection releaseConnectionFromDAO() {
        Connection connection = getConnection();
        this.conn = null;
        lock.unlock();
        return connection;
    }

    private void saveDependencies(E e) throws DAOException {
        Field[] fields = e.getClass().getDeclaredFields();
        for (Field field : fields) {
            AbstractDAO dao = factoryDAO.getDAO(field.getType());

            if (dao != null) {
                try {
                    field.setAccessible(true);
                    dao.setConnection(conn);
                    dao.create((Entity) field.get(e));
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } finally {
                    dao.releaseConnectionFromDAO();
                }
            }

        }

    }

    private void updateDependencies(E e) throws DAOException {

        Field[] fields = e.getClass().getDeclaredFields();
        for (Field field : fields) {
            log.trace(field.getClass());
            AbstractDAO dao = factoryDAO.getDAO(field.getClass());

            if (dao != null) {
                dao.update(e);

            }
        }
    }

    public FactoryDAO getFactoryDAO() {
        return factoryDAO;
    }
}
