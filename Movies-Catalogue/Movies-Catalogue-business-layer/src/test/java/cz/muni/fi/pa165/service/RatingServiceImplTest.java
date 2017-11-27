package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.builders.RatingBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dao.RatingDao;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author Marek Urban
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RatingServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Mock
    private RatingDao ratingDao;

    @Mock
    private TimeService timeService;

    private Rating testRating;

    private RatingBuilder ratingBuilder;

    @Autowired
    @InjectMocks
    private RatingService ratingService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.ratingBuilder = new RatingBuilder();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Rating expectedRating = ratingBuilder.id(id).actorRtg(37).build();

        when(ratingDao.findById(id)).thenReturn(expectedRating);

        assertThat(ratingService.findById(id)).isEqualTo(expectedRating);
        verify(ratingDao, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratingBuilder.id(1L).actorRtg(11).build());
        expectedRatings.add(ratingBuilder.id(2L).actorRtg(22).build());
        expectedRatings.add(ratingBuilder.id(3L).actorRtg(33).build());

        when(ratingDao.findAll()).thenReturn(expectedRatings);

        assertThat(ratingService.findAll()).containsAll(expectedRatings);
        verify(ratingDao, times(1)).findAll();
    }

    @Test
    public void testFindByMovie() {
        List<Rating> expectedRatings = new ArrayList<>();
        Long id = 1L;
        Long movieId = 2L;
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Terminator");
        expectedRatings.add(ratingBuilder.id(id).actorRtg(20).movie(movie).build());

        when(ratingDao.findByMovieId(movieId)).thenReturn(expectedRatings);

        assertThat(ratingService.findByMovie(movie)).containsAll(expectedRatings);
        verify(ratingDao, times(1)).findByMovieId(movieId);

    }

    @Test
    public void testCreate() {
        Rating calledRating = ratingBuilder.actorRtg(70).overallRtg(80).build();
        Long id = 1L;
        Rating expectedRating = ratingBuilder.actorRtg(70).overallRtg(80).id(id).build();

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((Rating) args[0]).setId(id);
            return null;
        }).when(ratingDao).create(calledRating);

        expectedRating.setId(id);
        assertThat(ratingService.create(calledRating)).isEqualTo(expectedRating.getId());
        verify(ratingDao, times(1)).create(expectedRating);

    }

    @Test
    public void testUpdate() {
        Rating expectedRating = ratingBuilder.actorRtg(70).overallRtg(80).id(1L).build();

        //doAnswer(null).when(ratingDao).update(expectedRating);

        assertThat(ratingService.update(expectedRating)).isEqualTo(expectedRating);
        verify(ratingDao, times(1)).update(expectedRating);
    }

    @Test
    public void testDelete() {
        Rating expectedRating = ratingBuilder.actorRtg(70).overallRtg(80).id(1L).build();

        //doAnswer(null).when(ratingDao).delete(expectedRating.getId());

        ratingService.delete(expectedRating.getId());
        verify(ratingDao, times(1)).delete(expectedRating.getId());
    }

    @Test
    public void testGetAverageRatingWithEmptyList() {
        List<Rating> ratings = new ArrayList<>();
        Rating expectedRating = ratingBuilder.overallRtg(0).actorRtg(0).scenarioRtg(0).build();

        assertThat(ratingService.getAverageRating(ratings)).isEqualTo(expectedRating);
    }


    @Test
    public void testGetAverageRatingWithOneItemInList() {
        List<Rating> ratings = new ArrayList<>();
        Rating expectedRating = ratingBuilder.overallRtg(75).actorRtg(80).scenarioRtg(70).build();
        ratings.add(expectedRating);

        assertThat(ratingService.getAverageRating(ratings)).isEqualTo(expectedRating);
    }


    @Test
    public void testGetAverageRatingWithTwoEqualInList() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(ratingBuilder.overallRtg(60).actorRtg(60).scenarioRtg(70).build());
        ratings.add(ratingBuilder.overallRtg(80).actorRtg(40).scenarioRtg(80).build());

        Rating expectedRating = ratingBuilder.overallRtg(70).actorRtg(50).scenarioRtg(75).build();

        assertThat(ratingService.getAverageRating(ratings)).isEqualTo(expectedRating);
    }


    @Test
    public void testGetAverageRatingWithLowestAndHighestRating() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(ratingBuilder.overallRtg(0).actorRtg(0).scenarioRtg(0).build());
        ratings.add(ratingBuilder.overallRtg(100).actorRtg(100).scenarioRtg(100).build());

        Rating expectedRating = ratingBuilder.overallRtg(50).actorRtg(50).scenarioRtg(50).build();

        assertThat(ratingService.getAverageRating(ratings)).isEqualTo(expectedRating);
    }

    @Test
    public void testGetSimplifiedRatingValueWithNullEntity() {
        assertThat(ratingService.getSimplifiedRatingValue(null)).isEqualTo(0);
    }


    @Test
    public void testGetSimplifiedRatingValueWithZeroValues() {
        Rating rating = ratingBuilder.overallRtg(0).actorRtg(0).scenarioRtg(0).build();
        assertThat(ratingService.getSimplifiedRatingValue(rating)).isEqualTo(0);
    }

    @Test
    public void testGetSimplifiedRatingValueWithLowestMiddleAndHighestValue() {
        Rating rating = ratingBuilder.overallRtg(0).actorRtg(50).scenarioRtg(100).build();
        assertThat(ratingService.getSimplifiedRatingValue(rating)).isEqualTo(50);
    }

    @Test
    public void testGetSimplifiedRatingValueWithUnsetRatings() {
        Rating rating = ratingBuilder.actorRtg(50).scenarioRtg(100).build();
        assertThat(ratingService.getSimplifiedRatingValue(rating)).isEqualTo(50);
    }
}
