package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(Api.ROOT_URI_GENRES)
public class GenresController {

    final static Logger logger = LoggerFactory.getLogger(GenresController.class);

    //TODO: test for bugs, certainly the methods will throw some exceptions for null pointers

    @Inject
    private GenreFacade genreFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDto> index() {
        return genreFacade.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final GenreDto create(@RequestBody GenreDto dto) throws Exception {
        try {
            logger.debug("dto incoming: "+dto.toString());
            Long id = genreFacade.create(dto);
            return genreFacade.findById(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenreDto get(@PathVariable("id") Long id) throws Exception {
        GenreDto dto = genreFacade.findById(id);
        if (dto == null) {
            // TODO: add exceptions to the project
            throw new Exception("not found");
        }
        return dto;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final GenreDto update(@PathVariable("id") Long id, @RequestBody GenreDto dto) throws Exception {
        try {
            GenreDto stored = genreFacade.findById(id);
            stored.setDescription(!dto.getDescription().equals("") ? dto.getDescription() : stored.getDescription());
            stored.setName(!dto.getName().equals("") ? dto.getName() : stored.getName());

            return genreFacade.update(stored);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void delete(@PathVariable("id") Long id) throws Exception {
        try {
            GenreDto stored = genreFacade.findById(id);
            genreFacade.delete(stored);
        } catch (Exception ex) {
            throw new Exception("does not exist");
        }
    }

}
