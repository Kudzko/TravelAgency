package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.daoobjectdesc.OrderDAODesc;
import by.andersen.kudko.webapp.model.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends AbstractDAO<Order, Integer> {
    public final String INSERT_USER = "INSERT INTO `travel_agency`.`order` (`user_id`, `country_id`) VALUES (?, ?);";
    public final String FIND_BY_PK = "SELECT `id`,`user_id`, `country_id`  FROM `travel_agency`.`order` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`order` SET `user_id` = ?, `country_id` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`order` WHERE (`id` = ?);";

    @Override
    public String createQuery() {
        return INSERT_USER;
    }

    @Override
    public void createPrpStmt(Order entity, PreparedStatement prpStmt) throws SQLException {
        OrderDAODesc.preparedStatementDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Order entity, PreparedStatement prpStmt) throws SQLException {
        OrderDAODesc.preparedStatementDescription(entity, prpStmt);
        prpStmt.setInt(3, entity.getId());
    }

    @Override
    public String deleteSql() {
        return DELETE_BY_ID;
    }

    @Override
    public void deletePrpStmt(Order entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getId());
    }

    @Override
    public String getByPKSQL() {
        return FIND_BY_PK;
    }

    @Override
    public Order resultsetStringToObject(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        OrderDAODesc.resultsetStringToObjectDescription(order, resultSet);
        return order;
    }
}
