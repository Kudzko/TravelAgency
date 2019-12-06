package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Order;
import by.andersen.kudko.webapp.model.entity.Tour;
import by.andersen.kudko.webapp.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends AbstractDAO<Order, Integer> {
    public final String INSERT_ORDER = "INSERT INTO `travel_agency`.`order` (`user_id`, `tour_id`) VALUES (?, ?);";
    public final String FIND_BY_PK = "SELECT `id`,`user_id`, `tour_id`  FROM `travel_agency`.`order` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`order` SET `user_id` = ?, `tour_id` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`order` WHERE (`id` = ?);";

    @Override
    public String createQuery() {
        return INSERT_ORDER;
    }

    @Override
    public void createPrpStmt(Order entity, PreparedStatement prpStmt) throws SQLException {
        orderDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Order entity, PreparedStatement prpStmt) throws SQLException {
        orderDescription(entity, prpStmt);
        prpStmt.setInt(3, entity.getId());
    }

    private void orderDescription(Order entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getUser().getId());
        prpStmt.setInt(2, entity.getTour().getId());
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
    public Order resultsetToObject(ResultSet resultSet, Connection connection) throws SQLException, DAOException {
        Order order = new Order();
        // gets id
        order.setId(resultSet.getInt("id"));

        // gets user
        Integer userId = resultSet.getInt("user_id");
        User user = (User) getDependentEntityById(User.class, connection, userId);
        order.setUser(user);

        // gets tour
        Integer tourId = resultSet.getInt("tour_id");
        Tour tour = (Tour) getDependentEntityById(Tour.class, connection, tourId);
        order.setTour(tour);
        return order;
    }
}
