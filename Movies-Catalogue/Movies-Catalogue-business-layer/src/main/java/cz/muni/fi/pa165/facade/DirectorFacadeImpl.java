/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.DirectorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Maros
 */
public class DirectorFacadeImpl implements DirectorFacade {

    @Autowired
    private DirectorService directorService;

    @Autowired
    private BeanMappingService mapper;

    @Override
    public Long create(DirectorDto director) {
        return directorService.create(mapper.mapTo(director, Director.class));
    }

    @Override
    public DirectorDto update(DirectorDto director) {
        return mapper.mapTo(directorService.update(mapper.mapTo(director, Director.class)), DirectorDto.class);
    }

    @Override
    public void delete(DirectorDto director) {
        directorService.delete(director.getId());
    }

    @Override
    public DirectorDto findById(Long id) {
        return mapper.mapTo(directorService.findById(id), DirectorDto.class);
    }

    @Override
    public List<DirectorDto> findAll() {
        return mapper.mapTo(directorService.findAll(), DirectorDto.class);
    }

}
