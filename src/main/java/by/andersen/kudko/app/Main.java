package by.andersen.kudko.app;

import by.andersen.kudko.dao.jdbctemplate.jdbctemplateimpl.UserDAO;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContextMVC.xml");
        Properties dbProperties = new Properties();
        DriverManagerDataSource dataSource = null;
        try {
            dbProperties.load(new FileInputStream("src/main/resources/db.properties"));
            String driverClass = dbProperties.getProperty("db.driverClass");
            String url = dbProperties.getProperty("db.url");
            String dbUser = dbProperties.getProperty("db.user");
            String dbPass = dbProperties.getProperty("db.password");


            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driverClass);
            dataSource.setUrl(url);
            dataSource.setUsername(dbUser);
            dataSource.setPassword(dbPass);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        UserDAO userDAO = new UserDAO(dataSource);
        System.out.println(userDAO.findUserByName("Ivan"));



    }
}
