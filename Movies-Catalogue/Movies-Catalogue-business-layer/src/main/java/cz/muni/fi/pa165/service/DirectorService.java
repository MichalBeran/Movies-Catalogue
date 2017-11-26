/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Movie;
import java.util.List;

/**
 * Service layer for {@link Director} entity.
 *
 * @author Maros
 */
public interface DirectorService {

    /**
     * Finds {@link Director} by ID
     *
     * @param id desired identifier
     * @return Director entity
     */
    Director findById(Long id);

    /**
     * Finds all {@link Director} entities
     *
     * @return list of Directors
     */
    List<Director> findAll();

    /**
     * Creates new {@link Director} entity
     *
     * @param entity created entity
     * @return id of created entity
     */
    Long create(Director entity);

    /**
     * Updates {@link Director} entity
     *
     * @param entity created entity
     * @return id of created entity
     */
    Director update(Director entity);

    /**
     * Deletes {@link Director} entity
     *
     * @param id identifier
     */
    void delete(Long id);
}
