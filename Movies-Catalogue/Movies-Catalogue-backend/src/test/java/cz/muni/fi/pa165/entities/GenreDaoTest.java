package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.dao.GenreDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;
import java.util.logging.Level;

import static org.assertj.core.api.Assertions.*;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Marek
 */
public class GenreDaoTest {


    private GenreDaoImpl dao;
    private EntityManager em;

    private Genre action;
    private Genre scifi;
    
    @BeforeClass
    public static void setUpEMFactory(){
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
    }
    
    @Before
    public void setUp() throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new GenreDaoImpl();
        dao.setEntityManager(em);

        setEntities();
    }

    private void setEntities(){
        action = new Genre();
        action.setName("Action");
        action.setDescription("Action movies");

        scifi = new Genre();
        scifi.setName("Sci-fi");
        scifi.setDescription("Science-fiction movies");
    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test
    public void testStore() {
        em.getTransaction().begin();
        dao.create(action);
        em.getTransaction().commit();

        assertThat(action.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        em.getTransaction().begin();
        dao.create(action);
        em.getTransaction().commit();

        Long id = action.getId();
        assertThat(id).isNotNull();

        Genre fromStorage = dao.findById(id);

        assertThat(fromStorage).isNotNull();

        assertThat(fromStorage.getName()).isEqualTo(action.getName());
        assertThat(fromStorage.getDescription()).isEqualTo(action.getDescription());
    }

    @Test
    public void testUpdate() {
        em.getTransaction().begin();
        dao.create(action);
        em.getTransaction().commit();

        Long id = action.getId();
        assertThat(id).isNotNull();

        Genre genreFromStorage = dao.findById(id);

        assertThat(genreFromStorage).isNotNull();
        String description = "Action movies are best";
        genreFromStorage.setDescription(description);

        em.getTransaction().begin();
        dao.update(genreFromStorage);
        em.getTransaction().commit();

        genreFromStorage = dao.findById(id);
        String descriptionFromStorage = genreFromStorage.getDescription();
        assertThat(descriptionFromStorage.equals(description));
        java.util.logging.Logger.getAnonymousLogger().log(Level.INFO, descriptionFromStorage);
    }

    @Test
    public void testDelete() {
        em.getTransaction().begin();
        dao.create(action);
        em.getTransaction().commit();

        Long id = action.getId();
        assertThat(id).isNotNull();

        em.getTransaction().begin();
        dao.delete(id);
        em.getTransaction().commit();

        try {
            Genre nonexistent = dao.findById(id);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testFindAll() {
        em.getTransaction().begin();
        dao.create(action);
        dao.create(scifi);
        em.getTransaction().commit();

        List<Genre> genres = dao.findAll();

        assertThat(genres.size()).isEqualTo(2);
    }

}
