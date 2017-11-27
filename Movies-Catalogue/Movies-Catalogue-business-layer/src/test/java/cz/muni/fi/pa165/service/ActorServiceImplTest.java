package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.builders.ActorBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dao.ActorDao;
import cz.muni.fi.pa165.entities.Actor;
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
public class ActorServiceImplTest extends AbstractJUnit4SpringContextTests {


    @Mock
    private ActorDao actorDao;

    @Mock
    private TimeService timeService;

    private Actor testActor;

    private ActorBuilder actorBuilder;

    @Autowired
    @InjectMocks
    private ActorService actorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.actorBuilder = new ActorBuilder();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        Actor calledActor = actorBuilder.firstName("Lebowski").lastName("chua").build();
        Long id = 1L;
        Actor expectedActor = actorBuilder.id(1L).firstName("Lebowski").lastName("chua").build();

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Actor) args[0]).setId(id);
            return null;
        }).when(actorDao).create(calledActor);

        expectedActor.setId(id);
        assertThat(actorService.create(calledActor)).isEqualTo(expectedActor.getId());
        verify(actorDao, times(1)).create(expectedActor);
    }
    @Test
    public void testDelete() {
        Actor expectedActor = actorBuilder.firstName("Lebowski").lastName("chua").build();

        //doAnswer(null).when(ratingDao).delete(expectedRating.getId());

        actorService.delete(expectedActor.getId());
        verify(actorDao, times(1)).delete(expectedActor.getId());
    }

    @Test
    public void testUpdate() {
        Actor expectedActor = actorBuilder.firstName("Lebowski").lastName("chua").build();

        //doAnswer(null).when(ratingDao).update(expectedRating);

        assertThat(actorService.update(expectedActor)).isEqualTo(expectedActor);
        verify(actorDao, times(1)).update(expectedActor);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Actor expectedActor = actorBuilder.firstName("Lebowski").lastName("chua").build();

        when(actorDao.findById(id)).thenReturn(expectedActor);

        assertThat(actorService.findById(id)).isEqualTo(expectedActor);
        verify(actorDao, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Actor> expectedActors = new ArrayList<>();
        expectedActors.add(actorBuilder.firstName("Lebowski").lastName("chua").build());
        expectedActors.add(actorBuilder.firstName("Angelina").lastName("jolie").build());
        expectedActors.add(actorBuilder.firstName("Petr").lastName("Rychly").build());

        when(actorDao.findAll()).thenReturn(expectedActors);

        assertThat(actorService.findAll()).containsAll(expectedActors);
        verify(actorDao, times(1)).findAll();
    }

}
