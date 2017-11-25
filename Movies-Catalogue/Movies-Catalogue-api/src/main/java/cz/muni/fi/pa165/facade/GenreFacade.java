package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;

import java.util.List;

/**
 * @author Marek Urban
 */
public interface GenreFacade {

    /**
     * Finds {@link GenreDto} by ID
     *
     * @param id desired identifier
     * @return GenreDto
     */
    GenreDto findById(Long id);

    /**
     * Finds {@link GenreDto} by name
     *
     * @param name desired name
     * @return GenreDto
     */
    GenreDto findByName(String name);

    /**
     * Finds all {@link GenreDto}s
     *
     * @return list of GenreDto
     */
    List<GenreDto> findAll();

    /**
     * Finds all {@link GenreDto} by movie
     *
     * @param movie MovieDto
     * @return GenreDto
     */
    List<GenreDto> findByMovie(MovieDto movie);

    /**
     * Saves new {@link GenreDto}
     *
     * @param dto GenreDto
     * @return identifier
     */
    Long create(GenreDto dto);

    /**
     * Updates {@link GenreDto}
     *
     * @param dto GenreDto
     * @return updated dto
     */
    GenreDto update(GenreDto dto);

    /**
     * Deletes {@link GenreDto}
     *
     * @param dto GenreDto
     */
    void delete(GenreDto dto);
}
