package by.andersen.kudko.webapp.dao;

import by.andersen.kudko.webapp.dao.daoobjectdesc.UserDescription;
import by.andersen.kudko.webapp.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User, Integer> {
    public final String INSERT_USER = "INSERT INTO `travel_agency`.`user` (`name`, `surname`, `nickname`) VALUES (?, ?, ?);";
    public final String FIND_BY_PK = "SELECT `id`,`name`, `surname`, `nickname`  FROM `travel_agency`.`user` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`user` SET `name` = ?, `surname` = ?, `nickname` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`user` WHERE (`id` = ?);";

    @Override
    public String createQuery() {
        return INSERT_USER;
    }

    @Override
    public void createPrpStmt(User entity, PreparedStatement prpStmt) throws SQLException {
        UserDescription.preparedStatementDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(User entity, PreparedStatement prpStmt) throws SQLException {
        UserDescription.preparedStatementDescription(entity, prpStmt);
        prpStmt.setInt(4, entity.getId());
    }

    @Override
    public String deleteSql() {
        return DELETE_BY_ID;
    }

    @Override
    public void deletePrpStmt(User entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getId());
    }

    @Override
    public String getByPKSQL() {
        return FIND_BY_PK;
    }

    @Override
    public User resultsetStringToObject(ResultSet resultSet) throws SQLException {
        User user = new User();

        UserDescription.resultsetStringToObjectDescription(user, resultSet);

        return user;
    }


}