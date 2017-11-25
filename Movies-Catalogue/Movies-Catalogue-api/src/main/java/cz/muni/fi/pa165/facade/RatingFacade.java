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
    
}
