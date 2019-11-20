package by.andersen.kudko.webapp;

import by.andersen.kudko.webapp.dao.*;
import by.andersen.kudko.webapp.dao.connection.ConnectionPoolException;
import by.andersen.kudko.webapp.dao.connection.second.ConnectionPool;
import by.andersen.kudko.webapp.dao.connection.second.DBParameter;
import by.andersen.kudko.webapp.dao.connection.second.DBResourceManager;
import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.*;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class Test {
    public static void main(String[] args) {


        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        String dbDriver = dbResourceManager.getValue(DBParameter.DRIVER);
        String dbUrl = dbResourceManager.getValue(DBParameter.DB_URL);
        String dbUser = dbResourceManager.getValue(DBParameter.USER_DB);
        String dbPass = dbResourceManager.getValue(DBParameter.PASS_DB);

        String testSQL = "SELECT * FROM travel_agency.user u;";


        Connection con = null;
        try {
            ConnectionPool connectionPool = new ConnectionPool();

            try {

                User user = new User("Vasya", "Test", "TestNick");
                Country country = new Country("FranceTest");
                Hotel hotel = new Hotel("TestHotelNane", 5);
                Tour tour = new Tour(hotel, country, "TEST TEST TEST review");
                Order order = new Order(user, tour);

                con = connectionPool.takeConnection();

                FactoryDAO factoryDAO = FactoryDAO.getInstance();
//                TourDAO tourDAO = (TourDAO) factoryDAO.getDAO(Tour.class);
//                tourDAO.setConnection(con);
//                // tourDAO.create(tour);
//                Tour tour1 = tourDAO.getByPK((Integer)22);
//                tourDAO.releaseConnectionFromDAO();
//                 System.out.println(tour1);
//TEST OTEL DAO
//                AbstractDAO hotelDAO = factoryDAO.getDAO(Hotel.class);
//                hotelDAO.setConnection(con);
//                 Hotel hotel1 = (Hotel) hotelDAO.getByPK((Integer)8);
//                System.out.println(hotel1);
// TEST USER DAO
//                UserDAO userDAO = (UserDAO) factoryDAO.getDAO(User.class);
//                userDAO.setConnection(con);
//                User testUser = userDAO.getByPK((Integer) 1);
//                System.out.println(testUser);
                OrderDAO orderDAO = (OrderDAO) factoryDAO.getDAO(Order.class);
                orderDAO.setConnection(con);
                orderDAO.create(order);
                orderDAO.releaseConnectionFromDAO();



            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            } catch (DAOException e) {
                e.printStackTrace();
            } finally {
                connectionPool.returnConnection(con);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}