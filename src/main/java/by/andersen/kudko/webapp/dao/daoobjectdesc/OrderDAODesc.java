package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.dao.exception.DAOException;
import by.andersen.kudko.webapp.model.entity.Order;
import by.andersen.kudko.webapp.model.entity.Tour;
import by.andersen.kudko.webapp.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class OrderDAODesc extends BaseDesc {
    private OrderDAODesc() {
    }

    public static void preparedStatementDescription(Order entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getUser().getId());
        prpStmt.setInt(2, entity.getTour().getId());
    }

    public static void resultsetStringToObjectDescription(Order entity, ResultSet resultSet, Connection connection) throws SQLException, DAOException {
        entity.setId(resultSet.getInt("id"));
        // gets user
        Integer userId = resultSet.getInt("user_id");
        User user = (User) getEntityById(User.class, connection, userId);
        entity.setUser(user);

        // gets tour
        Integer tourId = resultSet.getInt("tour_id");
        Tour tour = (Tour) getEntityById(Tour.class, connection, tourId);
        entity.setTour(tour);

    }


}
