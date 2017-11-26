/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.builders.MovieBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author Dominik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MovieServiceImplTest extends AbstractJUnit4SpringContextTests{
    
    @Mock
    private MovieDao movieDao;
    
    @Mock
    private TimeService timeService;
    
    @Autowired
    @InjectMocks
    private MovieService movieService;
    
    private MovieBuilder movieBuilder;
    
    @Before
    public void setUp() {
        
        MockitoAnnotations.initMocks(this);
        movieBuilder = new MovieBuilder();
    }
    
    @After
    public void tearDown() {
    }
    
    private Movie testMovie(int y, int m, int d){
        Movie movie = new Movie();
        movie.setTitle("MovieTitle");
        movie.setDateOfRelease(LocalDate.of(y, m, d));
        movie.setDescription("Date of relase" + y + m + d + ".");
        movie.setImage("IHaveNoImage");
        return movie;
    }

    /**
     * Test of getNewestMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetNewestMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(movieBuilder.dateOfRelase(LocalDate.of(2015,1,1)).build());
        movies.add(movieBuilder.dateOfRelase(LocalDate.of(2016,1,1)).build());
        movies.add(movieBuilder.dateOfRelase(LocalDate.of(2017,1,1)).build());
        
        when(movieDao.findAll()).thenReturn(movies);
        when(timeService.getCurrentDate()).thenReturn(LocalDate.of(2017, Month.DECEMBER, 1));
        
        List<Movie> foundMovies = movieService.getNewestMovies(2);
        //assertThat(foundMovies).containsOnly(movies.get(1), movies.get(2));
    }

    
}
