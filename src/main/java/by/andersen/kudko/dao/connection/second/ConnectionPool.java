package by.andersen.kudko.dao.connection.second;

import by.andersen.kudko.dao.connection.ConnectionPoolException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
public final class ConnectionPool {
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenConnections;

    private String driverName;
    private String url;
    private String user;
    private String pass;
    private int poolSize;
    private final int DEFAULT_POOL_SIZE = 5;
    // flag to check if there ane instances of Connection in pool
    private boolean isInited;

    public ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.USER_DB);
        this.pass = dbResourceManager.getValue(DBParameter.PASS_DB);
        try {
            String connectionAmount = dbResourceManager.getValue(DBParameter.AMOUNT_CONNECTIONS);
            this.poolSize = Integer.parseInt(connectionAmount);
        } catch (NumberFormatException e) {
            this.poolSize = DEFAULT_POOL_SIZE;
        }
    }


    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            givenConnections = new ArrayBlockingQueue<Connection>(poolSize);
            connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection con = DriverManager.getConnection(url, user, pass);
                // PooledConnection pooledConnection = new PooledConnection(con);
                // connectionQueue.add(pooledConnection);
                connectionQueue.add(con);
            }
        } catch (ClassNotFoundException e) {
            log.warn("Couldn't find DB driver class", e);
            throw new ConnectionPoolException("Couldn't find DB driver class", e);
        } catch (SQLException e) {
            log.warn("SQLException during connection pool initialization", e);
            throw new ConnectionPoolException("SQLException during connection pool initialization", e);
        }
        isInited = true;
    }

    /**
     * Closes all connections in pool connection
     */
    public void disposeAllConnections() {
        clearConnectionQueue();
        isInited = false;
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionQueue(givenConnections);
            closeConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            log.warn("Error closing connection in queue", e);
        }
    }


    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Connection isn't return to the pool.");
                e.printStackTrace();
            }
        } else {
            log.error("connection == null");
        }
    }

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection con;
        while (!queue.isEmpty()) {
            con = queue.poll();
            if (!con.getAutoCommit()) {
                con.commit();
            }
            closeConnection(con);
        }
    }

    /**
     * @return Connection to DB from pool connection
     * @throws ConnectionPoolException
     */
    public Connection takeConnection() throws ConnectionPoolException {
        Connection con = null;

        //if pool wasn't inited before, we init one right now
        if (!isInited){
            initPoolData();
        }

        try {
            con = connectionQueue.take();
            givenConnections.add(con);
        } catch (InterruptedException e) {
            String msg = "Couldn't take connection from pool connection";
            log.warn(msg, e);
            throw new ConnectionPoolException(msg, e);
        }

        return con;
    }

    /**
     * returns connection to pool connection
     */
    public void returnConnection(Connection con) throws ConnectionPoolException {
        givenConnections.remove(con);
        connectionQueue.add(con);
    }

}
