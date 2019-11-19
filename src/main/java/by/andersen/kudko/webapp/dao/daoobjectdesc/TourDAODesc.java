package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.dao.AbstractDAO;
import by.andersen.kudko.webapp.dao.connection.ConnectionPoolException;
import by.andersen.kudko.webapp.dao.connection.second.ConnectionPool;
import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class TourDAODesc {
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
            AbstractDAO tempCountryDAO = new AbstractDAO() {
                @Override
                public String createQuery() {
                    return null;
                }

                @Override
                public void createPrpStmt(Entity entity, PreparedStatement prpStmt) throws SQLException {

                }

                @Override
                public String updateSql() {
                    return null;
                }

                @Override
                public void updatePrpStmt(Entity entity, PreparedStatement prpStmt) throws SQLException {

                }

                @Override
                public String deleteSql() {
                    return null;
                }

                @Override
                public void deletePrpStmt(Entity entity, PreparedStatement prpStmt) throws SQLException {

                }

                @Override
                public String getByPKSQL() {
                    return "SELECT `id`,`name` FROM `travel_agency`.`country` u WHERE u.id = ?;";
                }

                @Override
                public Entity resultsetStringToObject(ResultSet resultSet) throws SQLException {
                    Country country = new Country();
                    CountryDAODesc.resultsetStringToObjectDescription(country, resultSet);
                    return country;
                }
            };


            tempCountryDAO.setConnection(connection);
            Integer countyId = resultSet.getInt("country");
            Country country = (Country) tempCountryDAO.getByPK(countyId);
            entity.setCountry(country);



            // gets Hotel entity
            AbstractDAO tempHotelDAO = new AbstractDAO() {
                @Override
                public String createQuery() {
                    return null;
                }

                @Override
                public void createPrpStmt(Entity entity, PreparedStatement prpStmt) throws SQLException {

                }

                @Override
                public String updateSql() {
                    return null;
                }

                @Override
                public void updatePrpStmt(Entity entity, PreparedStatement prpStmt) throws SQLException {

                }

                @Override
                public String deleteSql() {
                    return null;
                }

                @Override
                public void deletePrpStmt(Entity entity, PreparedStatement prpStmt) throws SQLException {

                }

                @Override
                public String getByPKSQL() {
                    return "SELECT `id`,`name`, `stars` FROM `travel_agency`.`hotel` u WHERE u.id = ?;";
                }

                @Override
                public Entity resultsetStringToObject(ResultSet resultSet) throws SQLException {
                    Hotel hotel = new Hotel();
                    HotelDAODesc.resultsetStringToObjectDescription(hotel, resultSet);
                    return hotel;
                }
            };


            tempHotelDAO.setConnection(tempCountryDAO.releaseConnectionFromDAO());
            Integer hotelId = resultSet.getInt("hotel");
            Hotel hotel = (Hotel) tempCountryDAO.getByPK(hotelId);
            entity.setHotel(hotel);
            tempHotelDAO.releaseConnectionFromDAO();


        } catch (DAOException e) {
            e.printStackTrace();
        }

        entity.setReview(resultSet.getString("review"));
    }

}
