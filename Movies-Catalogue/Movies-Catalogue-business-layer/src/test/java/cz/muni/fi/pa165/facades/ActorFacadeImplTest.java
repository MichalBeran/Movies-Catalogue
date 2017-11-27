package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.builders.ActorDtoBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
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

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Maros on 11/27/2017.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActorFacadeImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ActorFacade actorFacade;

    private ActorDtoBuilder actorBuilder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        actorBuilder = new ActorDtoBuilder();
    }

    @After
    public void tearDown() {
        List<ActorDto> actorDtos = actorFacade.findAll();
        for (ActorDto dto : actorDtos) {
            actorFacade.delete(dto);
        }
    }

    @Test
    public void testFindAll() {

        actorFacade.create(
                actorBuilder.firstName("Lebowski").lastName("chua").dateOfBirth(LocalDate.of(1975, 6, 4)).build());
        actorFacade.create(
                actorBuilder.firstName("Angelina").lastName("jolie").build());

        List<ActorDto> dtos = actorFacade.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test
    public void testCreate() {
        ActorDto expectedDto = actorBuilder.firstName("Lebowski").lastName("chua").build();
        expectedDto.setId(actorFacade.create(expectedDto));

        assertThat(expectedDto.getId()).isNotNull();
    }

    @Test
    public void testUpdate() {
        ActorDto expectedDto = actorBuilder.firstName("Lebowski").lastName("chua").build();
        expectedDto.setId(actorFacade.create(expectedDto));

        ActorDto actualDto = actorFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
        expectedDto.setFirstName("Joey");

        actualDto = actorFacade.update(expectedDto);

        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testFindById(){
        ActorDto expectedDto = actorBuilder.firstName("Lebowski").lastName("chua").build();
        expectedDto.setId(actorFacade.create(expectedDto));

        ActorDto actualDto = actorFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
    }


}
