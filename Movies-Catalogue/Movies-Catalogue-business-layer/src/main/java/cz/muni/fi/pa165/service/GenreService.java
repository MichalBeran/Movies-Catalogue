package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;

import java.util.List;

/**
 * Service layer for {@link Genre} entity.
 *
 * @author Marek Urban
 */
public interface GenreService {

    /**
     * Finds {@link Genre} by ID
     *
     * @param id desired identifier
     * @return Genre entity
     */
    Genre findById(Long id);

    /**
     * Finds {@link Genre} by its name
     *
     * @param name desired name
     * @return Genre entity
     */
    Genre findByName(String name);

    /**
     * Finds all {@link Genre} entities
     *
     * @return list of Genres
     */
    List<Genre> findAll();

    /**
     * Finds all {@link Genre} entities for given Movie
     *
     * @param movie Movie
     * @return list of Genres
     */
    List<Genre> findByMovie(Movie movie);

    /**
     * Creates new {@link Genre} entity
     * @param entity created entity
     * @return id of created entity
     */
    Long create(Genre entity);
}
