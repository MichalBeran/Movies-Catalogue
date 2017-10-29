/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Genre;
import java.util.List;

/**
 *
 * @author Dominik
 */
public interface GenreDao {
    /**
     * Persists a newly created entity
     *
     * @param entity entity to create
     */
    void create(Genre entity);

    /**
     * Update saved entity
     *
     * @param entity entity to create
     */
    void update(Genre entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<Genre> a list of entities
     */
    List<Genre> findAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return Genre the found entity
     */
    Genre findById(Long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     */
    void delete(Long id);
}
