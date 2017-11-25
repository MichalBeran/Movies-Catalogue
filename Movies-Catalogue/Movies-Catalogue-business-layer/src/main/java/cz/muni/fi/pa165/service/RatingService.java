package cz.muni.fi.pa165.service;


import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;

import java.util.List;

/**
 * Service layer for {@link Rating} entity.
 *
 * @author Marek Urban
 */
public interface RatingService {

    /**
     * Finds {@link Rating} by ID
     *
     * @param id desired identifier
     * @return Rating entity
     */
    Rating findById(Long id);

    /**
     * Finds all {@link Rating} entities
     *
     * @return list of Ratings
     */
    List<Rating> findAll();

    /**
     * Finds all {@link Rating} entities for given Movie
     *
     * @param movie Movie
     * @return list of Ratings
     */
    List<Rating> findByMovie(Movie movie);

    /**
     * Creates new {@link Rating} entity
     * @param entity created entity
     * @return id of created entity
     */
    Long create(Rating entity);
}
