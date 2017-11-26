/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.builders.MovieBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author Dominik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MovieFacadeImplTest extends AbstractJUnit4SpringContextTests{
    
    private MovieBuilder movieBuilder;
    
    @Autowired
    private MovieFacade movieFacade;
    
    public MovieFacadeImplTest() {
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
     * Test of getRecommendedMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetRecommendedMovies() {
        List<Movie> movies = new ArrayList<>();
        Genre action = new Genre("Action");
        Genre romantic = new Genre("Romantic");
        Genre drama = new Genre("Drama");
        Genre fantastic = new Genre("Fantastic");
        
        movies.add(movieBuilder.genres(action).genres(drama).build());
        movies.add(movieBuilder.genres(action).genres(romantic).build());
        movies.add(movieBuilder.genres(action).build());
        
        Movie model = movieBuilder.genres(action).build();
        List<Movie> foundMovies = movieFacade.getRecommendedMovies(model);
        //assertThat(foundMovies).containsAll(movies);
        
        model = movieBuilder.genres(drama).build();
        foundMovies = movieFacade.getRecommendedMovies(model);
        //assertThat(foundMovies).containsOnly(movies.get(0));
        
        model = movieBuilder.genres(fantastic).build();
        foundMovies = movieFacade.getRecommendedMovies(model);
        //Assert.assertNotNull(foundMovies);
        //assertThat(foundMovies).isEmpty();
    }
    
}
