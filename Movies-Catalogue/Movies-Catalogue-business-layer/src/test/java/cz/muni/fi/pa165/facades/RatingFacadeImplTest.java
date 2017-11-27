package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.builders.RatingDtoBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.dto.RatingDto;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.facade.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    private RatingDtoBuilder ratingDtoBuilder;

    private MovieDto testMovie;
    private UserDto testUser;
    private UserDto testUser2;

    @Autowired
    private MovieFacade movieFacade;

    @Autowired
    private ActorFacade actorFacade;

    @Autowired
    private DirectorFacade directorFacade;

    @Autowired
    private UserFacade userFacade;

    // TODO: wait for implementation of other parts of system

    @Test
    public void dummyTest(){}
    /*
    @BeforeClass
    public void init(){
        testMovie = new MovieDto();
        testUser = new UserDto();
        testUser2 = new UserDto();
    }

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        ratingDtoBuilder = new RatingDtoBuilder();

    }

    @After
    public void tearDown() {
        List<RatingDto> ratingDtos = ratingFacade.findAll();
        for (RatingDto dto : ratingDtos) {
            ratingFacade.delete(dto);
        }
    }

    @Test
    public void testFindAll() {
        ratingFacade.create(
                ratingDtoBuilder.actorRtg(100).overallRtg(100).scenarioRtg(100).movie(testMovie).user(testUser).build());
        ratingFacade.create(
                ratingDtoBuilder.actorRtg(100).overallRtg(100).scenarioRtg(50).movie(testMovie).user(testUser2).build());

        List<RatingDto> dtos = ratingFacade.findAll();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Test
    public void testCreate() {
        RatingDto expectedDto = ratingDtoBuilder.actorRtg(100).overallRtg(100).scenarioRtg(100).movie(testMovie).user(testUser).build();
        expectedDto.setId(ratingFacade.create(expectedDto));

        assertThat(expectedDto.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        RatingDto expectedDto = ratingDtoBuilder.actorRtg(100).overallRtg(100).scenarioRtg(100).movie(testMovie).user(testUser).build();
        expectedDto.setId(ratingFacade.create(expectedDto));

        RatingDto actualDto = ratingFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testUpdate() {
        RatingDto expectedDto = ratingDtoBuilder.actorRtg(100).overallRtg(100).scenarioRtg(100).movie(testMovie).user(testUser).build();
        expectedDto.setId(ratingFacade.create(expectedDto));

        RatingDto actualDto = ratingFacade.findById(expectedDto.getId());
        assertThat(actualDto).isEqualTo(expectedDto);
        expectedDto.setOverallRating(0);

        actualDto = ratingFacade.update(expectedDto);

        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testFindByMovie() {

    }

    @Test
    public void testGetAverageRating(){
        List<RatingDto> ratings = new ArrayList<>();
        ratings.add(ratingDtoBuilder.actorRtg(100).overallRtg(100).scenarioRtg(100).build());
        ratings.add(ratingDtoBuilder.actorRtg(0).overallRtg(0).scenarioRtg(0).build());
        RatingDto expectedDto = ratingDtoBuilder.actorRtg(50).overallRtg(50).scenarioRtg(50).build();

        RatingDto actualDto = ratingFacade.getAverageRating(ratings);
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void testGetSimplifiedRatingValue(){

        RatingDto dto = ratingDtoBuilder.actorRtg(0).overallRtg(50).scenarioRtg(100).build();
        int expectedResult = 50;

        int actualResult = ratingFacade.getSimplifiedRatingValue(dto);
        assertThat(actualResult).isEqualTo(expectedResult);
    }*/
}
