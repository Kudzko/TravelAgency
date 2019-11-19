package by.andersen.kudko.webapp.dao.connection.first;

public class DBProperty {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL
            = "jdbc:mysql://127.0.0.1:3306/travel_agency?characterEncoding" +
            "=UTF-8&serverTimezone=UTC";
    public static final String USER_DB = "root";
    public static final String PASS_DB = "root";
    public static final int AMOUNT_CONNECTIONS = 10;
}
