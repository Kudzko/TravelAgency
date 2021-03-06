package by.andersen.kudko.dao.springData;

import by.andersen.kudko.domain.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO extends AbstractDAO<Country, Integer> {
    public final String INSERT_USER = "INSERT INTO `travel_agency`.`country` (`country_name`) VALUES (?);";
    public final String FIND_BY_PK = "SELECT `id`,`country_name` FROM `travel_agency`.`country` u WHERE u.id = ?;";
    public final String UPDATE_BY_ID = " UPDATE `travel_agency`.`country` SET `country_name` = ? WHERE `id` = ?;";
    public final String DELETE_BY_ID = " DELETE FROM `travel_agency`.`country` WHERE (`id` = ?);";

    @Override
    public String createQuery() {
        return INSERT_USER;
    }

    @Override
    public void createPrpStmt(Country entity, PreparedStatement prpStmt) throws SQLException {
        countryDescription(entity, prpStmt);
    }

    @Override
    public String updateSql() {
        return UPDATE_BY_ID;
    }

    @Override
    public void updatePrpStmt(Country entity, PreparedStatement prpStmt) throws SQLException {
        countryDescription(entity, prpStmt);
        prpStmt.setInt(2, entity.getId());
    }

    private void countryDescription(Country entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setString(1, entity.getCountryName());
    }

    @Override
    public String deleteSql() {
        return DELETE_BY_ID;
    }

    @Override
    public void deletePrpStmt(Country entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setInt(1, entity.getId());
    }

    @Override
    public String getByPKSQL() {
        return FIND_BY_PK;
    }

    @Override
    public Country resultsetToObject(ResultSet resultSet, Connection connection) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setCountryName(resultSet.getString("country_name"));
        return country;
    }
}
