/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.dao.ActorDaoImpl;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.time.LocalDate;
import java.util.ArrayList;
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

import static org.assertj.core.api.Assertions.*;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Maros
 */
public class DirectorDaoTest {

    @PersistenceContext
    private EntityManager em;
    private DirectorDaoImpl dao;

    private Logger logger;

    private Genre comedy;
    private Actor angelinaJolie;
    private Director stevenSpielberg;
    private Director bay;
    private Director coen;

    private Movie movie1;
    private Movie movie2;
    private List<Movie> setOfMovies = new ArrayList<>();

    @BeforeClass
    public static void setUpEMFactory() {
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new DirectorDaoImpl();
        dao.setEntityManager(em);
        logger = LogManager.getLogger("DirectorDaoTest");

        setEntities();
    }

    private void setEntities() {
        comedy = new Genre();
        comedy.setName("Comedy");
        comedy.setDescription("Comedy movies");

        angelinaJolie = new Actor();
        angelinaJolie.setFirstName("Angelina");
        angelinaJolie.setLastName("Jolie");
        angelinaJolie.setDateOfBirth(LocalDate.of(1975, 6, 4));

        stevenSpielberg = new Director();
        stevenSpielberg.setFirstName("Steven");
        stevenSpielberg.setLastName("Spielberg");
        stevenSpielberg.setDateOfBirth(LocalDate.of(1946, 12, 18));

        em.getTransaction().begin();
        em.persist(comedy);
        em.persist(angelinaJolie);
        em.persist(stevenSpielberg);
        em.getTransaction().commit();

        List<Actor> actors = new ArrayList<>();
        actors.add(angelinaJolie);

        movie1 = new Movie();
        movie1.setTitle("The Big Lebowski");
        movie1.setDateOfRelease(LocalDate.of(1998, 6, 3));
        movie1.setDirector(stevenSpielberg);
        movie1.setActors(actors);
        em.getTransaction().begin();
        em.persist(movie1);
        em.getTransaction().commit();

        movie2 = new Movie();
        movie2.setTitle("Armageddon ");
        movie2.setDateOfRelease(LocalDate.of(1998, 1, 7));
        movie2.setDirector(stevenSpielberg);
        movie2.setActors(actors);
        em.getTransaction().begin();
        em.persist(movie2);
        em.getTransaction().commit();
        
        setOfMovies.add(movie1);
        setOfMovies.add(movie2);

        bay = new Director();
        bay.setFirstName("Michael");
        bay.setLastName("Bay");
        bay.setDateOfBirth(LocalDate.of(1957, 12, 13));
        bay.setMovies(setOfMovies);
        
        coen = new Director();
        coen.setFirstName("Joel");
        coen.setLastName("Coen");
        coen.setDateOfBirth(LocalDate.of(1957, 12, 13));
        coen.setMovies(setOfMovies);
    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test
    public void testStore() {
        em.getTransaction().begin();
        dao.create(bay);
        em.getTransaction().commit();

        assertThat(bay.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        em.getTransaction().begin();
        dao.create(bay);
        em.getTransaction().commit();

        Long id = bay.getId();
        assertThat(id).isNotNull();

        Director fromStorage = dao.findById(id);

        assertThat(fromStorage).isNotNull();

        assertThat(fromStorage.getFirstName()).isEqualTo(bay.getFirstName());
        assertThat(fromStorage.getLastName()).isEqualTo(bay.getLastName());
        assertThat(fromStorage.getDateOfBirth()).isEqualTo(bay.getDateOfBirth());
        assertThat(fromStorage.getMovies()).isEqualTo(bay.getMovies());
    }

    @Test
    public void testUpdate() {
        em.getTransaction().begin();
        dao.create(bay);
        em.getTransaction().commit();

        Long id = bay.getId();
        assertThat(id).isNotNull();

        Director directorFromStorage = dao.findById(id);

        assertThat(directorFromStorage).isNotNull();
        String name = "Explosion";
        directorFromStorage.setFirstName(name);

        em.getTransaction().begin();
        dao.update(directorFromStorage);
        em.getTransaction().commit();

        directorFromStorage = dao.findById(id);
        String nameFromStorage = directorFromStorage.getFirstName();
        assertThat(nameFromStorage.equals(name));

        logger.info(nameFromStorage);

    }

    @Test
    public void testDelete() {
        em.getTransaction().begin();
        dao.create(bay);
        em.getTransaction().commit();

        Long id = bay.getId();
        assertThat(id).isNotNull();

        em.getTransaction().begin();
        dao.delete(id);
        em.getTransaction().commit();

        try {
            Director nonexistent = dao.findById(id);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testFindAll() {
        em.getTransaction().begin();
        dao.create(bay);
        dao.create(coen);
        em.getTransaction().commit();

        List<Director> directors = dao.findAll();

        //bay + coen + spielberg
        assertThat(directors.size()).isEqualTo(3);
    }
}
