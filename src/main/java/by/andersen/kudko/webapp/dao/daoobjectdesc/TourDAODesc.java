package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.dao.AbstractDAO;
import by.andersen.kudko.webapp.dao.FactoryDAO;
import by.andersen.kudko.webapp.dao.connection.ConnectionPoolException;
import by.andersen.kudko.webapp.dao.connection.second.ConnectionPool;
import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class TourDAODesc extends BaseDesc {
    private TourDAODesc() {
    }

    public static void preparedStatementDescription(Tour entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getCountry().getId());
        prpStmt.setInt(2, entity.getHotel().getId());
        prpStmt.setString(3, entity.getReview());
    }

    public static void resultsetStringToObjectDescription(Tour entity, ResultSet resultSet, Connection connection) throws SQLException {
        entity.setId(resultSet.getInt("id"));


        try {
            // gets county entity
            Integer countyId = resultSet.getInt("country");
            Country country = (Country) getEntityById(Country.class, connection, countyId);
            entity.setCountry(country);

            // gets Hotel entity
            Integer hotelId = resultSet.getInt("hotel");
            Hotel hotel = (Hotel) getEntityById(Hotel.class, connection, hotelId);
            entity.setHotel(hotel);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        entity.setReview(resultSet.getString("review"));
    }


}
