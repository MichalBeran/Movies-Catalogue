/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Rating;
import java.util.List;

/**
 *
 * @author Dominik
 */
public interface RatingDao {
    /**
     * Persists a newly created entity
     *
     * @param entity entity to create
     */
    void create(Rating entity);

    /**
     * Update saved entity
     *
     * @param entity entity to create
     */
    void update(Rating entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<Rating> a list of entities
     */
    List<Rating> findAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return Rating the found entity
     */
    Rating findById(Long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     */
    void delete(Long id);

    /**
     * Finds all entities corresponding to movie id
     *
     * @param id movie id
     * @return list of ratings
     */
    List<Rating> findByMovieId(Long id);
}
