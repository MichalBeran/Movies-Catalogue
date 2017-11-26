package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.RatingDao;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDao ratingDao;

    @Override
    public Rating findById(Long id) {
        return ratingDao.findById(id);
    }

    @Override
    public List<Rating> findAll() {
        return ratingDao.findAll();
    }

    @Override
    public List<Rating> findByMovie(Movie movie) {
        return ratingDao.findByMovieId(movie.getId());
    }

    @Override
    public Long create(Rating entity) {
        ratingDao.create(entity);
        return entity.getId();
    }

    @Override
    public Rating update(Rating entity) {
        ratingDao.update(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        ratingDao.delete(id);
    }

    @Override
    public Rating getAverageRating(List<Rating> entities) {
        Rating averageRating = new Rating();
        averageRating.setActorsRating(0);
        averageRating.setScenarioRating(0);
        averageRating.setOverallRating(0);
        if (entities == null || entities.isEmpty()) {
            return averageRating;
        }
        int count = entities.size();
        if(count == 1){
            return entities.get(0);
        }

        for (Rating entity : entities) {
            averageRating.setScenarioRating(
                    averageRating.getScenarioRating() + entity.getScenarioRating());
            averageRating.setActorsRating(
                    averageRating.getActorsRating() + entity.getActorsRating());
            averageRating.setOverallRating(
                    averageRating.getOverallRating() + entity.getOverallRating());
        }

        averageRating.setScenarioRating(averageRating.getScenarioRating() / count);
        averageRating.setActorsRating(averageRating.getActorsRating() / count);
        averageRating.setOverallRating(averageRating.getOverallRating() / count);

        return averageRating;
    }

    @Override
    public int getSimplifiedRatingValue(Rating entity) {
        if(entity == null){
            return 0;
        }
        return (entity.getOverallRating() + entity.getActorsRating() + entity.getScenarioRating()) / 3;
    }
}
