/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dominik
 */
public class MovieServiceImplTest {
    
    public MovieServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class MovieServiceImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Movie m = null;
        MovieServiceImpl instance = new MovieServiceImpl();
        instance.create(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class MovieServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Movie m = null;
        MovieServiceImpl instance = new MovieServiceImpl();
        instance.delete(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class MovieServiceImpl.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        Movie m = null;
        MovieServiceImpl instance = new MovieServiceImpl();
        instance.edit(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class MovieServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        MovieServiceImpl instance = new MovieServiceImpl();
        Movie expResult = null;
        Movie result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetAllMovies() {
        System.out.println("getAllMovies");
        MovieServiceImpl instance = new MovieServiceImpl();
        List<Movie> expResult = null;
        List<Movie> result = instance.getAllMovies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewestMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetNewestMovies() {
        System.out.println("getNewestMovies");
        int number = 0;
        MovieServiceImpl instance = new MovieServiceImpl();
        List<Movie> expResult = null;
        List<Movie> result = instance.getNewestMovies(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecommendedMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetRecommendedMovies() {
        System.out.println("getRecommendedMovies");
        Movie m = null;
        MovieServiceImpl instance = new MovieServiceImpl();
        List<Movie> expResult = null;
        List<Movie> result = instance.getRecommendedMovies(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTopMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetTopMovies() {
        System.out.println("getTopMovies");
        Genre g = null;
        MovieServiceImpl instance = new MovieServiceImpl();
        List<Movie> expResult = null;
        List<Movie> result = instance.getTopMovies(g);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
