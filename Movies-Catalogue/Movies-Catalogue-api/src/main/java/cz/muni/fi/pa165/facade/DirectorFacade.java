/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.DirectorDto;
import java.util.List;

/**
 *
 * @author Maros
 */
public interface DirectorFacade {

    /**
     * Create a new director
     *
     * @param director to be created
     * @return id of created director
     */
    Long create(DirectorDto director);

    /**
     * Update director
     *
     * @param director to be updated
     * @return updated dto
     */
    DirectorDto update(DirectorDto director);

    /**
     * Delete director
     *
     * @param director to be deleted
     */
    void delete(DirectorDto director);

    /**
     * Find director by id
     *
     * @param id director id
     * @return director with given id
     */
    DirectorDto findById(Long id);

    /**
     * Find all directors
     *
     * @return all directors
     */
    List<DirectorDto> findAll();

}
