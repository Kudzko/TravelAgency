package by.andersen.kudko;

import by.andersen.kudko.daojpa.daoimpl.FactoryDAO;
import by.andersen.kudko.daojpa.daoimpl.UserDAO;
import by.andersen.kudko.daojpa.exception.DAOException;
import by.andersen.kudko.model.jpaentity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestJPA {
    public static void testJPAMethod() {
        User user = new User("Vasya", "Test", "TestNick");
        Country country = new Country("FranceTest");
        Hotel hotel = new Hotel("TestHotelNane", 5);
        Tour tour = new Tour(hotel, country, "TEST TEST TEST review");
        Order order = new Order(user, tour);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.persist(country);
        entityManager.persist(hotel);
        entityManager.persist(tour);
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void test1() {
        User user = new User();
        user.setName("Craig");
        user.setSurname("Walls");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void jpqlTest() {
        FactoryDAO factoryDAO = FactoryDAO.getInstance();
        UserDAO userDAO = (UserDAO) factoryDAO.getDAO(User.class);
        try {
            User user = new User("Vasya", "Test", "TestNick");
            userDAO.create(user);
            System.out.println("User created  " + user);
            user.setNickName("AnotherNickName");
            System.out.println("NickName changed" + user);
            userDAO.update(user);
            System.out.println("after Update" + userDAO.getById((Integer) user.getId()));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public static void findTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = new User("Vasya", "Test", "TestNick");
        //  entityManager.persist(user);

        System.out.println(entityManager.find(User.class, 87));
    }

    public static void findUsersByName(){
        FactoryDAO factoryDAO = FactoryDAO.getInstance();
        UserDAO userDAO = (UserDAO) factoryDAO.getDAO(User.class);
        System.out.println(userDAO.findByName("Vasya"));

    }
    public static void main(String[] args) {
//        test1();
        //  testJPAMethod();
//
//         findTest();
//        jpqlTest();

        findUsersByName();
    }
}
