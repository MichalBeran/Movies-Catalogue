package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.facade.MovieFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Marek Urban
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreFacadeImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private GenreFacade genreFacade;
/*

    @Autowired
    private MovieFacade movieFacade;

    @Autowired
    private ActorFacade actorFacade;

    @Autowired
    private DirectorFacade directorFacade;

*/
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        List<GenreDto> genreDtos = genreFacade.findAll();
        for (GenreDto dto : genreDtos) {
            genreFacade.delete(dto);
        }
    }

    @Test
    public void testFindAll() {
        createGenreDto("Sci-fi");
        createGenreDto("Action");

        List<GenreDto> dtos = genreFacade.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test
    public void testCreate() {
        GenreDto expectedDto = createGenreDto("Sci-fi");

        assertThat(expectedDto.getId()).isNotNull();
    }

    @Test
    public void testFindById(){
        GenreDto expectedDto = createGenreDto("Sci-fi");

        GenreDto actualDto = genreFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testFindByName(){
        String name = "Sci-fi";
        GenreDto expectedDto = createGenreDto(name);

        GenreDto actualDto = genreFacade.findByName(expectedDto.getName());
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testUpdate() {
        GenreDto expectedDto = createGenreDto("Sci-fi");

        assertThat(expectedDto.getId()).isNotNull();

        expectedDto.setDescription("Updated description");

        GenreDto actualDto = genreFacade.update(expectedDto);
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testFindByMovie(){
        /*
        createGenreDto("Sci-fi");
        createGenreDto("Action");
        List<GenreDto> genres = genreFacade.findAll();

        ActorDto actorDto = new ActorDto();
        actorDto.setDateOfBirth(LocalDate.of(1990,1,1));
        actorDto.setFirstName("Arnild");
        actorDto.setLastName("Schwarzenegger");

        actorFacade.create(actorDto);
        List<ActorDto> actors = actorFacade.findAll();

        DirectorDto directorDto = new DirectorDto();
        directorDto.setDateOfBirth(LocalDate.of(1980,1,1));
        directorDto.setFirstName("Emanuel");
        directorDto.setLastName("Bacigala");
        directorDto.setId(directorFacade.create(directorDto));


        CreateMovieDTO movieDto = new CreateMovieDTO();
        movieDto.setTitle("Terminator");
        movieDto.setGenres(genres);
        movieDto.setDateOfRelease(LocalDate.of(2017,01,01));
        movieDto.setActors(actors);
        movieDto.setDirector(directorDto);
        movieFacade.createMovie(movieDto);
        MovieDto savedMovieDto = (movieFacade.findAll()).get(0);
        assertThat(genreFacade.findByMovie(savedMovieDto)).isEqualTo(genres);
        */
    }

    GenreDto createGenreDto(String name){
        GenreDto dto = new GenreDto();
        dto.setName(name);
        dto.setDescription(name + " description");
        dto.setId(genreFacade.create(dto));
        return dto;
    }

    Genre createFakeGenre(String name, Long id){
        Genre entity = new Genre();
        entity.setName(name);
        entity.setDescription(name + " description");
        entity.setId(id);
        return entity;
    }
}
