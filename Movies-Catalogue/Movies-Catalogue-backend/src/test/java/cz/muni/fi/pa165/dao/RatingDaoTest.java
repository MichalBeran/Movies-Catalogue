package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.dao.GenreDaoImpl;
import cz.muni.fi.pa165.dao.RatingDao;
import cz.muni.fi.pa165.dao.RatingDaoImpl;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
import junit.framework.TestCase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marek
 */
public class RatingDaoTest {

    @PersistenceContext
    private EntityManager em;
    private RatingDaoImpl dao;
    private Logger logger;

    private Rating firstRating;
    private Rating secondRating;
    private Movie ratedMovie;

    @BeforeClass
    public static void setUpEMFactory(){
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new RatingDaoImpl();
        dao.setEntityManager(em);
        logger = LogManager.getLogger("tests");
        setEntities();
    }

    private void setEntities(){
        ratedMovie = new Movie();
        ratedMovie.setTitle("After Earth");
        ratedMovie.setDateOfRelease(LocalDate.of(2013, 6, 6));

        em.getTransaction().begin();
        em.persist(ratedMovie);
        em.getTransaction().commit();

        firstRating = new Rating();
        firstRating.setOverallRating(10);
        firstRating.setActorsRating(7);
        firstRating.setScenarioRating(8);
        firstRating.setMovie(ratedMovie);


        secondRating = new Rating();
        secondRating.setOverallRating(10);
        secondRating.setActorsRating(7);
        secondRating.setScenarioRating(8);
        secondRating.setMovie(ratedMovie);
    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test
    public void testStore() {
        em.getTransaction().begin();
        dao.create(firstRating);
        em.getTransaction().commit();

        assertThat(firstRating.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        em.getTransaction().begin();
        dao.create(firstRating);
        em.getTransaction().commit();

        Long id = firstRating.getId();
        assertThat(id).isNotNull();

        Rating fromStorage = dao.findById(id);

        assertThat(fromStorage).isNotNull();

        assertThat(fromStorage).isEqualTo(firstRating);
    }

    @Test
    public void testUpdate() {
        em.getTransaction().begin();
        dao.create(firstRating);
        em.getTransaction().commit();

        Long id = firstRating.getId();
        assertThat(id).isNotNull();

        Rating ratingFromStorage = dao.findById(id);

        assertThat(ratingFromStorage).isNotNull();
        ratingFromStorage.setActorsRating(10);

        em.getTransaction().begin();
        dao.update(ratingFromStorage);
        em.getTransaction().commit();

        Rating updatedRatingFromStorage = dao.findById(id);
        assertThat(updatedRatingFromStorage).isEqualTo(ratingFromStorage);

        logger.info(ratingFromStorage.toString());

    }

    @Test
    public void testDelete() {
        em.getTransaction().begin();
        dao.create(firstRating);
        em.getTransaction().commit();

        Long id = firstRating.getId();
        assertThat(id).isNotNull();

        em.getTransaction().begin();
        dao.delete(id);
        em.getTransaction().commit();

        try {
            Rating nonexistent = dao.findById(id);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testFindAll() {
        em.getTransaction().begin();
        dao.create(firstRating);
        dao.create(secondRating);
        em.getTransaction().commit();

        List<Rating> ratings = dao.findAll();

        assertThat(ratings.size()).isEqualTo(2);
    }

}
