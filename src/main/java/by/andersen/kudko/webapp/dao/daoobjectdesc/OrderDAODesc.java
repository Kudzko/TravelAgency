package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.model.entity.Order;
import by.andersen.kudko.webapp.model.entity.Tour;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class OrderDAODesc {
    private OrderDAODesc() {
    }

    public static void preparedStatementDescription(Order entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getUser().getId());
        prpStmt.setInt(2, entity.getTour().getId());
    }

    public static void resultsetStringToObjectDescription(Order entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
//        entity.setUser(resultSet.getString("user_id"));
//        entity.setTour(resultSet.getString("tour_id"));
    }


}
