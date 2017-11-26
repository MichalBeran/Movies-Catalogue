package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.dto.RatingDto;

import java.util.List;

/**
 * @author Marek Urban
 */
public interface RatingFacade {
    /**
     * Finds {@link RatingDto} by ID
     *
     * @param id desired identifier
     * @return RatingDto
     */
    RatingDto findById(Long id);

    /**
     * Finds all {@link RatingDto}s
     *
     * @return list of RatingDto
     */
    List<RatingDto> findAll();

    /**
     * Finds all {@link RatingDto} by movie
     *
     * @param movie MovieDto
     * @return RatingDto
     */
    List<RatingDto> findByMovie(MovieDto movie);

    /**
     * Saves new {@link RatingDto}
     *
     * @param dto RatingDto
     * @return identifier
     */
    Long create(RatingDto dto);

    /**
     * Updates {@link RatingDto}
     *
     * @param dto RatingDto
     * @return updated dto
     */
    RatingDto update(RatingDto dto);

    /**
     * Deletes {@link RatingDto}
     *
     * @param dto RatingDto
     */
    void delete(RatingDto dto);

    /**
     * Calculates the average {@link RatingDto} from a list of ratings
     * @param dtos list of ratings
     * @return average rating
     */
    RatingDto getAverageRating(List<RatingDto> dtos);

    /**
     * Calculates single numeric rating representation for
     * {@link RatingDto} rating values
     * @param dto RatingDto
     * @return simplified rating numeric representation in range 0-100
     */
    int getSimplifiedRatingValue(RatingDto dto);
}
