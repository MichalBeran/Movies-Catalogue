package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.service.GenreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Marek Urban
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GenreFacadeImplTest extends AbstractJUnit4SpringContextTests {

    @Mock
    private GenreService genreService;

    @Autowired
    @InjectMocks
    private GenreFacade genreFacade;

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
        List<GenreDto> expectedList = new ArrayList<>();
        GenreDto dto = createGenreDto("Sci-fi");
        GenreDto dto2 = createGenreDto("Action");
        expectedList.add(dto);
        expectedList.add(dto2);

        List<Genre> serviceList = new ArrayList<>();
        Genre ent = createFakeGenre("Sci-fi", 1L);
        Genre ent2 = createFakeGenre("Action", 2L);
        serviceList.add(ent);
        serviceList.add(ent2);

        MovieDto movieDto = new MovieDto();
        movieDto.setId(1L);

        Movie movie = new Movie();
        movie.setId(1L);
        when(genreService.findByMovie(any(Movie.class))).thenReturn(serviceList);
        assertThat(genreFacade.findByMovie(movieDto)).isEqualTo(expectedList);
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
