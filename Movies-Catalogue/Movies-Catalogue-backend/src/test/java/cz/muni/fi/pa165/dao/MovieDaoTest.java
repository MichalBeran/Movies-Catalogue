package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
/**
 * @author Michal
 */
public class MovieDaoTest {
    @PersistenceContext
    private EntityManager em;
    private MovieDaoImpl dao;

    private Logger logger;

    private Genre comedy;
    private Actor angelinaJolie;
    private Actor bradPitt;
    private Director stevenSpielberg;
    private Movie movie;

    @BeforeClass
    public static void setUpEMFactory(){
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new MovieDaoImpl();
        dao.setEntityManager(em);
        logger = LogManager.getLogger("GenreDaoTest");
        setEntities();
    }

    private void setEntities(){
        comedy = new Genre();
        comedy.setName("Comedy");
        comedy.setDescription("Comedy movies");

        angelinaJolie = new Actor();
        angelinaJolie.setFirstName("Angelina");
        angelinaJolie.setLastName("Jolie");
        angelinaJolie.setDateOfBirth(LocalDate.of(1975, 6, 4));

        bradPitt = new Actor();
        bradPitt.setFirstName("Brad");
        bradPitt.setLastName("Pitt");
        bradPitt.setDateOfBirth(LocalDate.of(1963, 12, 8));

        stevenSpielberg = new Director();
        stevenSpielberg.setFirstName("Steven");
        stevenSpielberg.setLastName("Spielberg");
        stevenSpielberg.setDateOfBirth(LocalDate.of(1946, 12, 18));

        em.getTransaction().begin();
        em.persist(comedy);
        em.persist(angelinaJolie);
        em.persist(bradPitt);
        em.persist(stevenSpielberg);
        em.getTransaction().commit();


        movie = new Movie();
        movie.setDescription("awesome movie");
        movie.setTitle("Best movie ever !");
        movie.setImage("not ready yet");
        movie.setDirector(stevenSpielberg);
        movie.setDateOfRelease(LocalDate.MIN);
        
        List<Movie> awesomeMovies = new ArrayList<>();
        awesomeMovies.add(movie);
        stevenSpielberg.setMovies(awesomeMovies);
        angelinaJolie.setMovies(awesomeMovies);
        bradPitt.setMovies(awesomeMovies);
        
        List<Actor> awesomeActors = new ArrayList<>();
        awesomeActors.add(angelinaJolie);
        awesomeActors.add(bradPitt);
        movie.setActors(awesomeActors);
        
        List<Genre> awesomeGenres = new ArrayList<>();
        awesomeGenres.add(comedy);
        movie.setGenres(awesomeGenres);
        comedy.setMovies(awesomeMovies);


    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullMovie(){
        em.getTransaction().begin();
        dao.create(null);
        em.getTransaction().commit();
    }

    @Test
    public void testCreate(){
        em.getTransaction().begin();
        dao.create(movie);
        em.getTransaction().commit();

        assertThat(movie.getId()).isNotNull();
    }

    @Test
    public void testFindById(){
        em.getTransaction().begin();
        dao.create(movie);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Movie foundMovie = dao.findById(movie.getId());
        
        assertThat(foundMovie).isNotNull();
        assertThat(foundMovie.getTitle()).isEqualTo(movie.getTitle());
        assertThat(foundMovie).isEqualTo(movie);
    }

    @Test
    public void testFindAllMovies(){
        em.getTransaction().begin();
        dao.create(movie);
        em.getTransaction().commit();
        
        List<Movie> movies = dao.findAll();
        assertThat(movies).contains(movie);
    }

    @Test
    public void testDelete(){
        em.getTransaction().begin();
        dao.create(movie);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        dao.delete(movie.getId());
        em.getTransaction().commit();
     
        assertThat(dao.findAll()).doesNotContain(movie);
    }
}
