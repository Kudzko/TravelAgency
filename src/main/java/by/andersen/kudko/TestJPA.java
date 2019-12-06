package by.andersen.kudko;

import by.andersen.kudko.jpaentity.Category;
import by.andersen.kudko.jpaentity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User();
        user.setName("Craig");
        user.setSurname("Walls");

        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

//        EntityManager em;
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Category cat = new Category();
//        cat.setTitle("new category");
//        // JUnit обеспечит тест свежим EntityManager'ом
//        em.persist(cat);
//        em.getTransaction().commit();

    }
}
