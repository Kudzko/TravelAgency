package by.andersen.kudko.dao.springData;

import by.andersen.kudko.domain.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDAO extends AbstractDAO<Hotel, Integer> {
    public final String INSERT_USER = "INSERT INTO `travel_agency`.`hotel` (`name`, `stars`) VALUES (?, ?);";
    public final String FIND_BY_PK = "SELECT `id`,`name`, `stars` FROM `travel_agency`.`hotel` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`hotel` SET `name` = ?, `stars` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`hotel` WHERE (`id` = ?);";


    @Override
    public String createQuery() {
        return INSERT_USER;
    }

    @Override
    public void createPrpStmt(Hotel entity, PreparedStatement prpStmt) throws SQLException {
        hotelDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Hotel entity, PreparedStatement prpStmt) throws SQLException {
        hotelDescription(entity, prpStmt);
        prpStmt.setInt(3, entity.getId());
    }

    private void hotelDescription(Hotel entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setString(1, entity.getHotelName());
        prpStmt.setInt(2, entity.getStars());
    }

    @Override
    public String deleteSql() {
        return DELETE_BY_ID;
    }

    @Override
    public void deletePrpStmt(Hotel entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getId());
    }

    @Override
    public String getByPKSQL() {
        return FIND_BY_PK;
    }

    @Override
    public Hotel resultsetToObject(ResultSet resultSet, Connection connection) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("id"));
        hotel.setHotelName(resultSet.getString("name"));
        hotel.setStars(resultSet.getInt("stars"));
        return hotel;
    }
}
