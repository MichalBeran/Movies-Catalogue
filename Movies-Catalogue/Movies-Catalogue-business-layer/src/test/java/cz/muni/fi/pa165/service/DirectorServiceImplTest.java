package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.builders.DirectorBuilder;
import cz.muni.fi.pa165.builders.GenreBuilder;
import cz.muni.fi.pa165.builders.GenreBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dao.ActorDao;
import cz.muni.fi.pa165.dao.DirectorDao;
import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Maros on 11/27/2017.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class DirectorServiceImplTest extends AbstractJUnit4SpringContextTests{

    @Mock
    private DirectorDao directorDao;

    @Mock
    private TimeService timeService;

    private Director testDirector;

    private DirectorBuilder directorBuilder;

    @Autowired
    @InjectMocks
    private DirectorServiceImpl directorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.directorBuilder = new DirectorBuilder();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        Director calledDirector = directorBuilder.firstName("Lebowski").lastName("chua").build();
        Long id = 1L;
        Director expectedDirector = directorBuilder.id(1L).firstName("Lebowski").lastName("chua").build();

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Director) args[0]).setId(id);
            return null;
        }).when(directorDao).create(calledDirector);

        expectedDirector.setId(id);
        assertThat(directorService.create(calledDirector)).isEqualTo(expectedDirector.getId());
        verify(directorDao, times(1)).create(expectedDirector);
    }
    @Test
    public void testDelete() {
        Director expectedDirector = directorBuilder.id(1L).firstName("Lebowski").lastName("chua").build();

        //doAnswer(null).when(ratingDao).delete(expectedRating.getId());

        directorService.delete(expectedDirector.getId());
        verify(directorDao, times(1)).delete(expectedDirector.getId());
    }

    @Test
    public void testUpdate() {
        Director expectedDirector = directorBuilder.id(1L).firstName("Lebowski").lastName("chua").build();

        //doAnswer(null).when(ratingDao).update(expectedRating);

        assertThat(directorService.update(expectedDirector)).isEqualTo(expectedDirector);
        verify(directorDao, times(1)).update(expectedDirector);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Director expectedDirector = directorBuilder.id(1L).firstName("Lebowski").lastName("chua").build();

        when(directorDao.findById(id)).thenReturn(expectedDirector);

        assertThat(directorService.findById(id)).isEqualTo(expectedDirector);
        verify(directorDao, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Director> expectedDirectors = new ArrayList<>();
        expectedDirectors.add(directorBuilder.firstName("Lebowski").lastName("chua").build());
        expectedDirectors.add(directorBuilder.firstName("Angelina").lastName("Jolie").build());
        expectedDirectors.add(directorBuilder.firstName("Petr").lastName("Rychly").build());

        when(directorDao.findAll()).thenReturn(expectedDirectors);

        assertThat(directorService.findAll()).containsAll(expectedDirectors);
        verify(directorDao, times(1)).findAll();
    }

}
