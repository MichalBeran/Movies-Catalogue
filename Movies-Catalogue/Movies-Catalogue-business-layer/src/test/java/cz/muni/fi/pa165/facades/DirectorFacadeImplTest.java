package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.facade.DirectorFacade;
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
import static org.mockito.Mockito.verify;

/**
 * Created by Maros on 11/27/2017.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DirectorFacadeImplTest extends AbstractJUnit4SpringContextTests {


    @Autowired
    private DirectorFacade directorFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        List<DirectorDto> directorDtos = directorFacade.findAll();
        for (DirectorDto dto : directorDtos) {
            directorFacade.delete(dto);
        }
    }


    DirectorDto createDirectorDto(String FirstName, String lastName, LocalDate dateOfBirth){
        DirectorDto dto = new DirectorDto();
        dto.setFirstName(FirstName);
        dto.setLastName(lastName);
        dto.setDateOfBirth(dateOfBirth);
        dto.setId(directorFacade.create(dto));
        return dto;
    }

    @Test
    public void testCreate() {
        DirectorDto expectedDto = createDirectorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        assertThat(expectedDto.getId()).isNotNull();
    }

    @Test
    public void testFindById(){
        DirectorDto expectedDto = createDirectorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        DirectorDto actualDto = directorFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testUpdate() {
        DirectorDto expectedDto = createDirectorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        assertThat(expectedDto.getId()).isNotNull();

        expectedDto.setLastName("Peterenko");

        DirectorDto actualDto = directorFacade.update(expectedDto);
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testFindAll() {
        createDirectorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));
        createDirectorDto("Head", "Hunterz", LocalDate.of(1975, 6, 4));

        List<DirectorDto> dtos = directorFacade.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test
    public void testDelete() {
        DirectorDto dto = createDirectorDto("Peter", "petrovic", LocalDate.of(1975, 6, 4));

        assertThat(directorFacade.findAll().size()).isEqualTo(1);
        directorFacade.delete(dto);
        assertThat(directorFacade.findAll().size()).isEqualTo(0);
    }

}
