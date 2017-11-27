package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.builders.GenreBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Genre;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author Marek Urban
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GenreServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Mock
    private GenreDao genreDao;

    private GenreBuilder genreBuilder;

    @Autowired
    @InjectMocks
    private GenreService genreService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.genreBuilder = new GenreBuilder();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Genre expectedGenre = genreBuilder.id(id).name("Action").build();

        when(genreDao.findById(id)).thenReturn(expectedGenre);

        assertThat(genreService.findById(id)).isEqualTo(expectedGenre);
        verify(genreDao, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Genre> expectedGenres = new ArrayList<>();
        expectedGenres.add(genreBuilder.id(1L).name("Action").build());
        expectedGenres.add(genreBuilder.id(2L).name("Sci-Fi").build());
        expectedGenres.add(genreBuilder.id(3L).name("Comedy").build());

        when(genreDao.findAll()).thenReturn(expectedGenres);

        assertThat(genreService.findAll()).containsAll(expectedGenres);
        verify(genreDao, times(1)).findAll();
    }

    @Test
    public void testFindByMovie() {
        List<Genre> expectedGenres = new ArrayList<>();
        Long id = 1L;
        expectedGenres.add(genreBuilder.id(id).name("Action").build());
        Long movieId = 2L;
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Terminator");
        movie.addGenre(expectedGenres.get(0));

        when(genreDao.findByMovieId(movieId)).thenReturn(expectedGenres);

        assertThat(genreService.findByMovie(movie)).containsAll(expectedGenres);
        verify(genreDao, times(1)).findByMovieId(movieId);

    }

    @Test
    public void testCreate() {
        Genre calledGenre = genreBuilder.name("Action").build();
        Long id = 1L;
        Genre expectedGenre = genreBuilder.id(id).name("Action").build();

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Genre) args[0]).setId(id);
            return null;
        }).when(genreDao).create(calledGenre);

        expectedGenre.setId(id);
        assertThat(genreService.create(calledGenre)).isEqualTo(expectedGenre.getId());
        verify(genreDao, times(1)).create(expectedGenre);

    }

    @Test
    public void testUpdate() {
        Genre expectedGenre = genreBuilder.name("Action").id(1L).build();

        assertThat(genreService.update(expectedGenre)).isEqualTo(expectedGenre);
        verify(genreDao, times(1)).update(expectedGenre);
    }

    @Test
    public void testDelete() {
        Genre expectedGenre = genreBuilder.name("Action").id(1L).build();

        genreService.delete(expectedGenre.getId());
        verify(genreDao, times(1)).delete(expectedGenre.getId());
    }
}
