/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.dao.ActorDaoImpl;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Movie;
import java.time.LocalDate;
import java.util.HashSet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;


/**
 *
 * @author Maros
 */
public class ActorDaoTest {

    @PersistenceContext
    private EntityManager em;
    private ActorDaoImpl dao;
    private Logger logger;

    private Actor buscemi;
    private Actor sandler;

    private Movie movie1;
    private Movie movie2;
    private Set<Movie> setOfMovies = new HashSet<>();

    @BeforeClass
    public static void setUpEMFactory() {
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new ActorDaoImpl();
        dao.setEntityManager(em);
        logger = LogManager.getLogger("ActorDaoTest");

        setEntities();
    }

    private void setEntities() {
        movie1 = new Movie();
        movie1.setTitle("The Big Lebowski");
        movie1.setDateOfRelease(LocalDate.of(1998, 6, 3));
        em.getTransaction().begin();
        em.persist(movie1);
        em.getTransaction().commit();

        movie2 = new Movie();
        movie2.setTitle("Armageddon ");
        movie2.setDateOfRelease(LocalDate.of(1998, 1, 7));
        em.getTransaction().begin();
        em.persist(movie2);
        em.getTransaction().commit();
        
        setOfMovies.add(movie1);
        setOfMovies.add(movie2);

        buscemi = new Actor();
        buscemi.setFirstName("Steve");
        buscemi.setLastName("Buscemi");
        buscemi.setDateOfBirth(LocalDate.of(1957, 12, 13));
        buscemi.setMovies(setOfMovies);
        
        sandler = new Actor();
        sandler.setFirstName("Adam");
        sandler.setLastName("Sandler");
        sandler.setDateOfBirth(LocalDate.of(1957, 12, 13));
        sandler.setMovies(setOfMovies);
    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test
    public void testStore() {
        em.getTransaction().begin();
        dao.create(buscemi);
        em.getTransaction().commit();

        assertThat(buscemi.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        em.getTransaction().begin();
        dao.create(buscemi);
        em.getTransaction().commit();

        Long id = buscemi.getId();
        assertThat(id).isNotNull();

        Actor fromStorage = dao.findById(id);

        assertThat(fromStorage).isNotNull();

        assertThat(fromStorage.getFirstName()).isEqualTo(buscemi.getFirstName());
        assertThat(fromStorage.getLastName()).isEqualTo(buscemi.getLastName());
        assertThat(fromStorage.getDateOfBirth()).isEqualTo(buscemi.getDateOfBirth());
        assertThat(fromStorage.getMovies()).isEqualTo(buscemi.getMovies());
    }

    @Test
    public void testUpdate() {
        em.getTransaction().begin();
        dao.create(buscemi);
        em.getTransaction().commit();

        Long id = buscemi.getId();
        assertThat(id).isNotNull();

        Actor actorFromStorage = dao.findById(id);

        assertThat(actorFromStorage).isNotNull();
        String name = "Rockhound";
        actorFromStorage.setFirstName(name);

        em.getTransaction().begin();
        dao.update(actorFromStorage);
        em.getTransaction().commit();

        actorFromStorage = dao.findById(id);
        String nameFromStorage = actorFromStorage.getFirstName();
        assertThat(nameFromStorage.equals(name));

        logger.info(nameFromStorage);

    }

    @Test
    public void testDelete() {
        em.getTransaction().begin();
        dao.create(buscemi);
        em.getTransaction().commit();

        Long id = buscemi.getId();
        assertThat(id).isNotNull();

        em.getTransaction().begin();
        dao.delete(id);
        em.getTransaction().commit();

        try {
            Actor nonexistent = dao.findById(id);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testFindAll() {
        em.getTransaction().begin();
        dao.create(buscemi);
        dao.create(sandler);
        em.getTransaction().commit();

        List<Actor> actors = dao.findAll();

        assertThat(actors.size()).isEqualTo(2);
    }

}
