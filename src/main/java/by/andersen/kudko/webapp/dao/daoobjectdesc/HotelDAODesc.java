package by.andersen.kudko.webapp.dao.daoobjectdesc;

import by.andersen.kudko.webapp.model.entity.Hotel;
import by.andersen.kudko.webapp.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class HotelDAODesc {
    private HotelDAODesc() {
    }

    public static void preparedStatementDescription(Hotel entity, PreparedStatement prpStmt) throws SQLException {
        prpStmt.setString(1, entity.getHotelName());
        prpStmt.setInt(2, entity.getStars());
    }

    public static void resultsetStringToObjectDescription(Hotel hotel, ResultSet resultSet) throws SQLException {
        hotel.setId(resultSet.getInt("id"));
        hotel.setHotelName(resultSet.getString("name"));
        hotel.setStars(resultSet.getInt("stars"));
    }
}
