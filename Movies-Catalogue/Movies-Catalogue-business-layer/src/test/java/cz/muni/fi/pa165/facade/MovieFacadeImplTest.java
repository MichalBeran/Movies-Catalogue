/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.CreateMovieDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *
 * @author Dominik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MovieFacadeImplTest extends AbstractTransactionalJUnit4SpringContextTests{
    
    @Autowired
    private MovieFacade movieFacade;
    
    public MovieFacadeImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetTopMovies() {
    }

    @Test
    public void testGetRecommendedMovies() {
    }

    @Test
    public void testGetNewestMovies() {
    }

    @Test
    public void testCreateMovie() {
        CreateMovieDto movieDto = new CreateMovieDto();
        movieFacade.createMovie(movieDto);
    }

    @Test
    public void testDeleteMovie() {
    }
    
}
