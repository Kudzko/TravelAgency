package by.andersen.kudko.dao.jdbctemplate.jdbctemplateimpl;

import by.andersen.kudko.dao.jdbctemplate.daointerface.IUserDAO;
import by.andersen.kudko.dao.jdbctemplate.exception.DAOException;
import by.andersen.kudko.model.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@NoArgsConstructor
@Component
public class UserDAO extends AbstractDAO<User, Integer> implements IUserDAO {

    public final String INSERT_USER = "INSERT INTO `travel_agency`.`user` (`name`, `surname`, `nickname`) VALUES (?, ?, ?);";
    public final String FIND_BY_PK = "SELECT `id`,`name`, `surname`, `nickname`  FROM `travel_agency`.`user` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`user` SET `name` = ?, `surname` = ?, `nickname` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`user` WHERE (`id` = ?);";
    public final String FIND_ALL = " SELECT * FROM `travel_agency`.`user`;";
    public final String FIND_BY_NAME = " SELECT * FROM `travel_agency`.`user` AS u WHERE u.name = ?;";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        return new User(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("nickname")
                );
    };

    public UserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findUserByName(String name) {
        return jdbcTemplate.query(FIND_BY_NAME, userRowMapper,name);
    }

    @Override
    public User find(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL,userRowMapper);
    }


    @Override
    public void createPrpStmt(User entity, PreparedStatement prpStmt) throws SQLException {

    }

    @Override
    public String updateSql() {
        return null;
    }

    @Override
    public void updatePrpStmt(User entity, PreparedStatement prpStmt) throws SQLException {

    }

    @Override
    public String deleteSql() {
        return null;
    }

    @Override
    public void deletePrpStmt(User entity, PreparedStatement prpStmt) throws SQLException {

    }

    @Override
    public String getByPKSQL() {
        return null;
    }

    @Override
    public User resultsetToObject(ResultSet resultSet, Connection connection) throws SQLException, DAOException {
        return null;
    }

    @Override
    public String createQuery() {
        return null;
    }
}