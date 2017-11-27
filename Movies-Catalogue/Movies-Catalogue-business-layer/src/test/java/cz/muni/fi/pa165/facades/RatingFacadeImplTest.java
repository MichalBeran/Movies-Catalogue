package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.builders.RatingDtoBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.facade.*;
import jdk.vm.ci.meta.Local;
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
import java.time.LocalDate;
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
    private static RatingFacade ratingFacade;

    private static RatingDtoBuilder ratingDtoBuilder;

    private static MovieDto testMovie;
    private static UserDto testUser;
    private static UserDto testUser2;

    @Autowired
    private static MovieFacade movieFacade;

    @Autowired
    private static ActorFacade actorFacade;

    @Autowired
    private static DirectorFacade directorFacade;

    @Autowired
    private static UserFacade userFacade;

    @Test
    public void dummyTest(){}

    @BeforeClass
    public static void init(){
        DirectorDto director = new DirectorDto();
        director.setDateOfBirth(LocalDate.of(1980,1,1));
        director.setFirstName("Director");
        director.setLastName("Happy");
        director.setId(directorFacade.create(director));

        ActorDto actor = new ActorDto();
        actor.setDateOfBirth(LocalDate.of(1985,1,1));
        actor.setFirstName("Jackie");
        actor.setLastName("Chan");
        actor.setId(actorFacade.create(actor));

        CreateMovieDto testMovie = new CreateMovieDto();
        testMovie.setDateOfRelease(LocalDate.of(2016,1,1));
        testMovie.setTitle("movie");
        testMovie.setDescription("description");
        RatingFacadeImplTest.testMovie = movieFacade.findById(movieFacade.createMovie(testMovie));


        testUser = new UserDto();
        testUser.setFirstName("John");
        testUser.setLastName("doe");
        testUser.setMail("m@m.m");
        testUser.setNick("nick");
        testUser.setPassword("pass");
        userFacade.registerUser(testUser, testUser.getPassword());
        testUser = userFacade.findUserByMail("m@m.m");


        testUser2 = new UserDto();
        testUser2.setFirstName("Johnny");
        testUser2.setLastName("doey");
        testUser2.setMail("m@mnm.m");
        testUser2.setNick("nickam");
        testUser2.setPassword("passw");
        userFacade.registerUser(testUser2, testUser2.getPassword());
        testUser2 = userFacade.findUserByMail("m@mnm.m");


        ratingDtoBuilder = new RatingDtoBuilder();
    }

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        //ratingDtoBuilder = new RatingDtoBuilder();

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
    }
}
