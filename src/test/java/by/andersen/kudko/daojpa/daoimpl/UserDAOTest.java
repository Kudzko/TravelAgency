package by.andersen.kudko.daojpa.daoimpl;

import by.andersen.kudko.jpaentity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

@Log4j2
public class UserDAOTest {
    private static UserDAO userDAO;

    @BeforeClass
    public static void initDAO() {
        FactoryDAO factoryDAO = FactoryDAO.getInstance();
        userDAO = (UserDAO) factoryDAO.getDAO(User.class);
        log.info("UserDAO " + userDAO);
    }

    @Test
    public void findBySurnameTest() {
        List<User> result = userDAO.findBySurname("Test");
        log.info(result);
        Assert.assertNotNull(result);
    }
}
