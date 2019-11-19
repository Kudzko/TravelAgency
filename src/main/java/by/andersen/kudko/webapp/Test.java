package by.andersen.kudko.webapp;

import by.andersen.kudko.webapp.dao.TourDAO;
import by.andersen.kudko.webapp.dao.UserDAO;
import by.andersen.kudko.webapp.dao.connection.ConnectionPoolException;
import by.andersen.kudko.webapp.dao.connection.second.ConnectionPool;
import by.andersen.kudko.webapp.dao.connection.second.DBParameter;
import by.andersen.kudko.webapp.dao.connection.second.DBResourceManager;
import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Country;
import by.andersen.kudko.webapp.model.entity.Hotel;
import by.andersen.kudko.webapp.model.entity.Tour;
import by.andersen.kudko.webapp.model.entity.User;

import java.sql.*;

public class Test {
    public static void main(String[] args) {

        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        String driver = dbResourceManager.getValue(DBParameter.DRIVER);
        String url = dbResourceManager.getValue(DBParameter.DB_URL);
        String user = dbResourceManager.getValue(DBParameter.USER_DB);
        String pass = dbResourceManager.getValue(DBParameter.PASS_DB);
        String testSQL = "SELECT * FROM travel_agency.user u;";


        Connection con = null;
        try {
            ConnectionPool connectionPool = new ConnectionPool();

            try {

                User user1 = new User("Vasya","Test", "TestNick");
                Country country = new Country("FranceTest");
                Hotel hotel = new Hotel("TestHotelNane", 5);
                Tour tour = new Tour(hotel, country, "TEST TEST TEST review");

                con = connectionPool.takeConnection();
//                UserDAO userDAO = new UserDAO();
//                userDAO.setConnection(con);
//                userDAO.create(user1);

                TourDAO tourDAO = new TourDAO();
                tourDAO.setConnection(con);
                tourDAO.create(tour);
                tourDAO.releaseConnectionFromDAO();

            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            } catch (DAOException e) {
                e.printStackTrace();
            }finally {
                connectionPool.returnConnection(con);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
