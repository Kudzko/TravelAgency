package by.andersen.kudko.webapp;


import by.andersen.kudko.webapp.jpaentity.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;


public class JPATest {
    private EntityManager em;

    @Before
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
        em = emf.createEntityManager();
    }

    @After
    public void close() {
        em.getEntityManagerFactory().close();
        em.close();
    }

    @Test
    public void tst_JpaEntity(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
        EntityManager entityManager = emf.createEntityManager();
    }


    @Test
    public void shouldPersistCategory() {
        Category cat = new Category();
        cat.setTitle("new category");
        // JUnit обеспечит тест свежим EntityManager'ом
        em.persist(cat);
    }

    @Test
    public void shouldFindCategory() {
        Category cat = new Category();
        cat.setTitle("test");
        em.persist(cat);
        Category result = em.find(Category.class, 1L);
        assertNotNull(result);
    }


}
