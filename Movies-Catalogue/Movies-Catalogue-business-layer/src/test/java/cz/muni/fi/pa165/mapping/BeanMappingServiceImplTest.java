/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mapping;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.DirectorDto;

import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author Dominik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceImplTest extends AbstractJUnit4SpringContextTests{
    
    CreateMovieDto createMovieDto;

    MovieDto movieDto;
    Movie movie;
    
    @Autowired
    BeanMappingService bms;
    
    public BeanMappingServiceImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testMapTo_Object_Class() {
        createMovieDto = new CreateMovieDto();
        createMovieDto.setTitle("title");
        createMovieDto.setDateOfRelease(LocalDate.MAX);
        createMovieDto.setDirector(new DirectorDto());
        movie = bms.mapTo(createMovieDto, Movie.class);
        assertThat(movie.getTitle()).isEqualTo("title");
    }

    
    @Test
    public void testMapTo_MovieDto(){
        Movie m = new Movie();
        m.setTitle("title2");
        m.setDateOfRelease(LocalDate.MAX);
        m.addActor(new Actor());
        m.addGenre(new Genre("Action"));
        m.setDirector(new Director());
        m.setId(Long.MIN_VALUE);
        movieDto = bms.mapTo(m, MovieDto.class);
        assertThat(movieDto).hasNoNullFieldsOrPropertiesExcept("description", "image");
    }

}
