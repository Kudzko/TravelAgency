package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.model.entity.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class CountryDAODesc {
    private CountryDAODesc() {
    }

    public static void preparedStatementDescription(Country entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setString(1, entity.getCountryName());
    }

    public static void resultsetStringToObjectDescription(Country entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
        entity.setCountryName(resultSet.getString("country_name"));
    }
}
