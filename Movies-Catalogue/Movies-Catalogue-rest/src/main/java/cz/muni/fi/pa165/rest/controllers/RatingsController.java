/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.RatingDto;
import cz.muni.fi.pa165.facade.RatingFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.rest.Api;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dominik
 */

@RestController
@RequestMapping(Api.ROOT_URI_RATINGS)
public class RatingsController {
    final static Logger logger = LoggerFactory.getLogger(MoviesController.class);

    @Autowired
    private BeanMappingService mapper;

    @Inject
    private RatingFacade ratingFacade;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RatingDto>> index() {
        return ResponseEntity.ok(mapper.mapTo(ratingFacade.findAll(), RatingDto.class));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDto> get(@PathVariable("id") Long id) throws Exception {
        try{
            RatingDto dto = ratingFacade.findById(id);
            return ResponseEntity.ok(mapper.mapTo(dto, RatingDto.class));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<RatingDto> create(@RequestBody RatingDto dto) throws Exception {
        try {
            Long id = ratingFacade.create(dto);
            return ResponseEntity.ok(mapper.mapTo(ratingFacade.findById(id), RatingDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<RatingDto> update(@PathVariable("id") Long id, @RequestBody RatingDto dto) throws Exception {
        try {
            RatingDto stored = ratingFacade.findById(id);
            stored.setActorsRating(dto.getActorsRating());
            stored.setOverallRating(dto.getOverallRating());
            stored.setScenarioRating(dto.getScenarioRating());
            stored.setDescription(dto.getDescription());
            
            return ResponseEntity.ok(mapper.mapTo(ratingFacade.update(stored), RatingDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            RatingDto stored = ratingFacade.findById(id);
            ratingFacade.delete(stored);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
