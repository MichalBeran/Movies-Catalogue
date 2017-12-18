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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
    
    @Mock
    private GenreService genreService;
    
    @Autowired
    @InjectMocks
    private MovieService movieService;
    
    private MovieBuilder movieBuilder;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
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
        movie.setImage("IHaveNoImage".getBytes());
        return movie;
    }

    /**
     * Test of getNewestMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetNewestMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new MovieBuilder().dateOfRelase(LocalDate.of(2015,1,1)).build());
        movies.add(new MovieBuilder().dateOfRelase(LocalDate.of(2016,1,1)).build());
        movies.add(new MovieBuilder().dateOfRelase(LocalDate.of(2017,1,1)).build());
        
        when(movieDao.findAll()).thenReturn(movies);
        when(timeService.getCurrentDate()).thenReturn(LocalDate.of(2017, Month.DECEMBER, 1));
        
        List<Movie> foundMovies = movieService.getNewestMovies(2);
        assertThat(foundMovies).containsOnly(movies.get(1), movies.get(2));
    }
    
    @Test
    public void testGetMoreNewMoviesAsExist(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new MovieBuilder().dateOfRelase(LocalDate.of(2015,1,1)).build());
        
        when(movieDao.findAll()).thenReturn(movies);
        when(timeService.getCurrentDate()).thenReturn(LocalDate.of(2017, Month.DECEMBER, 1));
        
        List<Movie> foundMovies = movieService.getNewestMovies(1);
        
        expectedException.expect(IllegalStateException.class);
        movieService.getNewestMovies(2);
    }

    /**
     * Test of getRecommendedMovies method, of class MovieServiceImpl.
     */
    @Test
    public void testGetRecommendedMovies() {
        List<Movie> movies = new ArrayList<>();
        Genre action = new Genre("Action");
        action.setId(Long.valueOf(1));
        Genre romantic = new Genre("Romantic");
        romantic.setId(Long.valueOf(2));
        Genre drama = new Genre("Drama");
        drama.setId(Long.valueOf(3));
        Genre fantastic = new Genre("Fantastic");
        fantastic.setId(Long.valueOf(4));
        
        movies.add(new MovieBuilder().id(Long.valueOf(1)).title("Film1").genres(action).genres(drama).build());
        movies.add(new MovieBuilder().id(Long.valueOf(2)).title("Film2").genres(action).genres(romantic).build());
        movies.add(new MovieBuilder().id(Long.valueOf(3)).title("Film3").genres(action).build());
        
        action.addMovie(movies.get(0));
        action.addMovie(movies.get(1));
        action.addMovie(movies.get(2));
        romantic.addMovie(movies.get(1));
        drama.addMovie(movies.get(0));
        
        when(genreService.findById(Long.valueOf(1))).thenReturn(action);
        when(genreService.findById(Long.valueOf(2))).thenReturn(romantic);
        when(genreService.findById(Long.valueOf(3))).thenReturn(drama);
        when(genreService.findById(Long.valueOf(4))).thenReturn(fantastic);
        
        Movie model = new MovieBuilder().genres(action).build();
        List<Movie> foundMovies = movieService.getRecommendedMovies(model);
        assertThat(foundMovies).containsAll(movies);
        
        model = new MovieBuilder().genres(drama).build();
        foundMovies = movieService.getRecommendedMovies(model);
        assertThat(foundMovies).containsOnly(movies.get(0));
        
        model = new MovieBuilder().genres(fantastic).build();
        foundMovies = movieService.getRecommendedMovies(model);
        Assert.assertNotNull(foundMovies);
        assertThat(foundMovies).isEmpty();
    }
    
}
