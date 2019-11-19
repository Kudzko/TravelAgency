package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final  class UserDescription {

    private UserDescription() {
    }

    public static void preparedStatementDescription(User entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setString(1, entity.getName());
        prpStmt.setString(2, entity.getSurname());
        prpStmt.setString(3, entity.getNickName());
    }

    public static void resultsetStringToObjectDescription(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setNickName(resultSet.getString("nickname"));
    }
}
