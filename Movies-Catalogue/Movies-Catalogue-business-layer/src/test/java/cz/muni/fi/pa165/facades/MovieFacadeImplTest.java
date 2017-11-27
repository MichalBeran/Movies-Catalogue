/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.builders.CreateMovieDtoBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
    private DirectorFacade directorFacade;
    
    @Autowired
    private ActorFacade actorFacade;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    private Long idA;
    private Long idD;
    
    
    
    public MovieFacadeImplTest() {
    }
    
    @BeforeClass
    public static void beforeClass(){
        
    }
    
    @Before
    public void setUp() {
        
        DirectorDto dir = new DirectorDto();
        dir.setFirstName("First");
        dir.setLastName("Last");
        idD = directorFacade.create(dir);
        
        ActorDto act = new ActorDto();
        act.setFirstName("Firsta");
        act.setLastName("Lasta");
        idA = actorFacade.create(act);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateMovie() {
        CreateMovieDto movieDto = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        
        Long id = movieFacade.createMovie(movieDto);
        assertThat(id).isNotNull();
    }

    
}
