package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.facade.ActorFacade;
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        List<ActorDto> actorDtos = actorFacade.findAll();
        for (ActorDto dto : actorDtos) {
            actorFacade.delete(dto);
        }
    }


    ActorDto createActorDto(String FirstName, String lastName, LocalDate dateOfBirth){
        ActorDto dto = new ActorDto();
        dto.setFirstName(FirstName);
        dto.setLastName(lastName);
        dto.setDateOfBirth(dateOfBirth);
        dto.setId(actorFacade.create(dto));
        return dto;
    }

    @Test
    public void testCreate() {
        ActorDto expectedDto = createActorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        assertThat(expectedDto.getId()).isNotNull();
    }

    @Test
    public void testFindById(){
        ActorDto expectedDto = createActorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        ActorDto actualDto = actorFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testUpdate() {
        ActorDto expectedDto = createActorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        assertThat(expectedDto.getId()).isNotNull();

        expectedDto.setLastName("Peterenko");

        ActorDto actualDto = actorFacade.update(expectedDto);
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testFindAll() {
        createActorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));
        createActorDto("Head", "Hunterz", LocalDate.of(1975, 6, 4));

        List<ActorDto> dtos = actorFacade.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test
    public void testDelete() {
        ActorDto dto = createActorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        assertThat(actorFacade.findAll().size()).isEqualTo(1);
        actorFacade.delete(dto);
        assertThat(actorFacade.findAll().size()).isEqualTo(0);
    }

}
