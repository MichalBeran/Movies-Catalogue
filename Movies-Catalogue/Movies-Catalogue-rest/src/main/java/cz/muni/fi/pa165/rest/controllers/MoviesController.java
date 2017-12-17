package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.detail.MovieDetailDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(Api.ROOT_URI_MOVIES)
public class MoviesController {

    final static Logger logger = LoggerFactory.getLogger(MoviesController.class);

    //TODO: test for bugs, certainly the methods will throw some exceptions for null pointers

    @Autowired
    private BeanMappingService mapper;

    @Inject
    private MovieFacade movieFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovieDetailDto> index() {
        return mapper.mapTo(movieFacade.findAll(), MovieDetailDto.class);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MovieDto create(@RequestBody CreateMovieDto dto) throws Exception {
        try {
            Long id = movieFacade.createMovie(dto);
            return movieFacade.findById(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto get(@PathVariable("id") Long id) throws Exception {
        MovieDto dto = movieFacade.findById(id);
        if (dto == null) {
            // TODO: add exceptions to the project
            throw new Exception("not found");
        }
        return dto;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MovieDto update(@PathVariable("id") Long id, @RequestBody MovieDto dto) throws Exception {
        try {
            MovieDto stored = movieFacade.findById(id);
            stored.setDescription(!dto.getDescription().equals("") ? dto.getDescription() : stored.getDescription());
            stored.setTitle(!dto.getTitle().equals("") ? dto.getTitle() : stored.getTitle());
            stored.setImage(!dto.getImage().equals("") ? dto.getImage() : stored.getImage());
            stored.setDirector(!dto.getDirector().equals("") ? dto.getDirector() : stored.getDirector());
            stored.setActors(!dto.getActors().equals("") ? dto.getActors() : stored.getActors());
            stored.setGenres(!dto.getGenres().equals("") ? dto.getGenres() : stored.getGenres());
            stored.setDateOfRelease(!dto.getDateOfRelease().equals("") ? dto.getDateOfRelease() : stored.getDateOfRelease());

            throw new Exception("PICE");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void delete(@PathVariable("id") Long id) throws Exception {
        try {
            MovieDto stored = movieFacade.findById(id);
            movieFacade.delete(stored);
        } catch (Exception ex) {
            throw new Exception("does not exist");
        }
    }

}
