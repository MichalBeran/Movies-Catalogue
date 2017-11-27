/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.DirectorService;
import java.time.LocalDate;
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
    
    @Autowired
    private DirectorService directorService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    private CreateMovieDto movieDto;
    
    public MovieFacadeImplTest() {
    }
    
    @Before
    public void setUp() {
        movieDto = new CreateMovieDto();
        Director d = new Director();
        d.setFirstName("First");
        d.setLastName("Last");
        directorService.create(d);
        movieDto.setDirector(beanMappingService.mapTo(d, DirectorDto.class));
        movieDto.setTitle("title");
        movieDto.setDateOfRelease(LocalDate.MAX);
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
        Long id = movieFacade.createMovie(movieDto);
        System.out.println("");
    }

    @Test
    public void testDeleteMovie() {
    }
    
}
