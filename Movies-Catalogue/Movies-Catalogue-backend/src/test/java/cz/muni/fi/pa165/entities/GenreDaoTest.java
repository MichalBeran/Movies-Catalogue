package cz.muni.fi.pa165.entities;

import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Marek
 */
public class GenreDaoTest extends TestCase {
    // TODO: implement GenreDao please
/*
    private GenreDao dao;
    private EntityManager em;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new GenreDaoImpl();
        dao.setEntityManager(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStore() {
        Genre genre = new Genre();
        genre.setName("Action");
        genre.setDescription("Action movies");

        em.getTransaction().begin();
        dao.create(genre);
        em.getTransaction().commit();

        assertThat(genre.getId()).isNotNull();
    }

    public void testFindById() {
        Genre genre = new Genre();
        genre.setName("Action");
        genre.setDescription("Action movies");

        em.getTransaction().begin();
        dao.create(genre);
        em.getTransaction().commit();

        Long id = genre.getId();
        assertThat(id).isNotNull();

        Genre fromStorage = dao.findById(id);

        assertThat(fromStorage).isNotNull();

        assertThat(fromStorage.getName()).isEqualTo(genre.getName());
        assertThat(fromStorage.getDescription()).isEqualTo(genre.getDescription());
    }

    public void testUpdate() {

    }

    public void testDelete() {
        Genre genre = new Genre();
        genre.setName("Action");
        genre.setDescription("Action movies");

        em.getTransaction().begin();
        dao.create(genre);
        em.getTransaction().commit();

        Long id = genre.getId();
        assertThat(id).isNotNull();

        em.getTransaction().begin();
        dao.delete(id);
        em.getTransaction().commit();

        try {
            Genre nonexistent = dao.findById(id);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testFindAll() {
        Genre genre = new Genre();
        genre.setName("Action");
        genre.setDescription("Action movies");
        Genre genre2 = new Genre();
        genre2.setName("Sci-fi");
        genre2.setDescription("Science-fiction movies");

        em.getTransaction().begin();
        dao.create(genre);
        dao.create(genre2);
        em.getTransaction().commit();

        List<Genre> genres = dao.findAll();

        assertThat(genres.size()).isEqualTo(2);
    }

*/
}
