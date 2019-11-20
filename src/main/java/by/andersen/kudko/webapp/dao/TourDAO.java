package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Country;
import by.andersen.kudko.webapp.model.entity.Hotel;
import by.andersen.kudko.webapp.model.entity.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TourDAO extends AbstractDAO<Tour, Integer> {
    public final String INSERT_TOUR = "INSERT INTO `travel_agency`.`tour` (`country`, `hotel`, `review`) VALUES (?, ?, ?);";
    public final String FIND_BY_PK = "SELECT `id`,`country`, `hotel`, `review`  FROM `travel_agency`.`tour` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`tour` SET `country` = ?, `hotel` = ?, `review` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`tour` WHERE (`id` = ?);";


    @Override
    public String createQuery() {
        return INSERT_TOUR;
    }

    @Override
    public void createPrpStmt(Tour entity, PreparedStatement prpStmt) throws SQLException {
        tourDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Tour entity, PreparedStatement prpStmt) throws SQLException {
        tourDescription(entity, prpStmt);
        prpStmt.setInt(4, entity.getId());
    }

    private void tourDescription(Tour entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getCountry().getId());
        prpStmt.setInt(2, entity.getHotel().getId());
        prpStmt.setString(3, entity.getReview());
    }

    @Override
    public String deleteSql() {
        return DELETE_BY_ID;
    }

    @Override
    public void deletePrpStmt(Tour entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getId());
    }

    @Override
    public String getByPKSQL() {
        return FIND_BY_PK;
    }

    @Override
    public Tour resultsetStringToObject(ResultSet resultSet, Connection connection) throws SQLException, DAOException {
        Tour tour = new Tour();

        // get id
        tour.setId(resultSet.getInt("id"));

        // gets county entity
        Integer countyId = resultSet.getInt("country");
        Country country = (Country) getDependentEntityById(Country.class, connection, countyId);
        tour.setCountry(country);

        // gets Hotel entity
        Integer hotelId = resultSet.getInt("hotel");
        Hotel hotel = (Hotel) getDependentEntityById(Hotel.class, connection, hotelId);
        tour.setHotel(hotel);

        // gets review
        tour.setReview(resultSet.getString("review"));

        return tour;
    }
}
