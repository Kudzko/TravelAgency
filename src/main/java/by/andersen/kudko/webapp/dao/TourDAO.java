package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.daoobjectdesc.TourDAODesc;
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
        TourDAODesc.preparedStatementDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Tour entity, PreparedStatement prpStmt) throws SQLException {
        TourDAODesc.preparedStatementDescription(entity, prpStmt);
        prpStmt.setInt(4, entity.getId());
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
    public Tour resultsetStringToObject(ResultSet resultSet, Connection connection) throws SQLException {
        Tour tour = new Tour();
        TourDAODesc.resultsetStringToObjectDescription(tour, resultSet, conn);
        return tour;
    }
}
