package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.dao.RatingDao;
import cz.muni.fi.pa165.dao.RatingDaoImpl;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Marek
 */
public class RatingDaoTest extends TestCase {


    private RatingDaoImpl dao;
    private EntityManager em;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        dao = new RatingDaoImpl();
        dao.setEntityManager(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStore() {
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testFindById() {
    }

    public void testFindAll() {
    }

}
