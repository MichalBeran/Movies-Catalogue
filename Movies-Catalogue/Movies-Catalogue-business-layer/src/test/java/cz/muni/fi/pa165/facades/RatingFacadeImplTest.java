package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.RatingDto;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.facade.RatingFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Marek Urban
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RatingFacadeImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private RatingFacade ratingFacade;
/*

    @Autowired
    private MovieFacade movieFacade;

    @Autowired
    private ActorFacade actorFacade;

    @Autowired
    private DirectorFacade directorFacade;

*/
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @After
//    public void tearDown() {
//        List<RatingDto> genreDtos = ratingFacade.findAll();
//        for (RatingDto dto : genreDtos) {
//            ratingFacade.delete(dto);
//        }
//    }
//
//    @Test
//    public void testFindAll() {
//        createRatingDto("Sci-fi");
//        createRatingDto("Action");
//
//        List<RatingDto> dtos = ratingFacade.findAll();
//        assertThat(dtos.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void testCreate() {
//        RatingDto expectedDto = createRatingDto("Sci-fi");
//
//        assertThat(expectedDto.getId()).isNotNull();
//    }
//
//    @Test
//    public void testFindById(){
//        RatingDto expectedDto = createRatingDto("Sci-fi");
//
//        RatingDto actualDto = ratingFacade.findById(expectedDto.getId());
//        assertThat(actualDto).isEqualTo(expectedDto);
//    }
//
//    @Test
//    public void testFindByName(){
//        String name = "Sci-fi";
//        RatingDto expectedDto = createRatingDto(name);
//
//        RatingDto actualDto = ratingFacade.findByName(expectedDto.getName());
//        assertThat(actualDto).isEqualTo(expectedDto);
//    }
//
//    @Test
//    public void testUpdate() {
//        RatingDto expectedDto = createRatingDto("Sci-fi");
//
//        assertThat(expectedDto.getId()).isNotNull();
//
//        expectedDto.setDescription("Updated description");
//
//        RatingDto actualDto = ratingFacade.update(expectedDto);
//        assertThat(actualDto).isEqualTo(expectedDto);
//    }
//
//    @Test
//    public void testFindByMovie(){
//        /*
//        createRatingDto("Sci-fi");
//        createRatingDto("Action");
//        List<RatingDto> genres = ratingFacade.findAll();
//
//        ActorDto actorDto = new ActorDto();
//        actorDto.setDateOfBirth(LocalDate.of(1990,1,1));
//        actorDto.setFirstName("Arnild");
//        actorDto.setLastName("Schwarzenegger");
//
//        actorFacade.create(actorDto);
//        List<ActorDto> actors = actorFacade.findAll();
//
//        DirectorDto directorDto = new DirectorDto();
//        directorDto.setDateOfBirth(LocalDate.of(1980,1,1));
//        directorDto.setFirstName("Emanuel");
//        directorDto.setLastName("Bacigala");
//        directorDto.setId(directorFacade.create(directorDto));
//
//
//        CreateMovieDTO movieDto = new CreateMovieDTO();
//        movieDto.setTitle("Terminator");
//        movieDto.setGenres(genres);
//        movieDto.setDateOfRelease(LocalDate.of(2017,01,01));
//        movieDto.setActors(actors);
//        movieDto.setDirector(directorDto);
//        movieFacade.createMovie(movieDto);
//        MovieDto savedMovieDto = (movieFacade.findAll()).get(0);
//        assertThat(ratingFacade.findByMovie(savedMovieDto)).isEqualTo(genres);
//        */
//    }
//
//    RatingDto createRatingDto(String name){
//        RatingDto dto = new RatingDto();
//        dto.setName(name);
//        dto.setDescription(name + " description");
//        dto.setId(ratingFacade.create(dto));
//        return dto;
//    }
//
//    Genre createFakeGenre(String name, Long id){
//        Genre entity = new Genre();
//        entity.setName(name);
//        entity.setDescription(name + " description");
//        entity.setId(id);
//        return entity;
//    }

    @Test
    public void testNull(){

    }
}
