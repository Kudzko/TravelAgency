package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.daoobjectdesc.HotelDAODesc;
import by.andersen.kudko.webapp.model.entity.Hotel;

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
        HotelDAODesc.preparedStatementDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Hotel entity, PreparedStatement prpStmt) throws SQLException {
        HotelDAODesc.preparedStatementDescription(entity, prpStmt);
        prpStmt.setInt(3, entity.getId());
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
    public Hotel resultsetStringToObject(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        HotelDAODesc.resultsetStringToObjectDescription(hotel , resultSet);
        return hotel;
    }
}
