package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(Api.ROOT_URI_DIRECTORS)
public class DirectorsController {

    final static Logger logger = LoggerFactory.getLogger(GenresController.class);

    //TODO: test for bugs, certainly the methods will throw some exceptions for null pointers

    @Inject
    private DirectorFacade directorFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DirectorDto> index() {
        return directorFacade.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DirectorDto create(@RequestBody DirectorDto dto) throws Exception {
        try {
            logger.debug("dto incoming: "+dto.toString());
            Long id = directorFacade.create(dto);
            return directorFacade.findById(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DirectorDto get(@PathVariable("id") Long id) throws Exception {
        DirectorDto dto = directorFacade.findById(id);
        if (dto == null) {
            // TODO: add exceptions to the project
            throw new Exception("not found");
        }
        return dto;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DirectorDto update(@PathVariable("id") Long id, @RequestBody DirectorDto dto) throws Exception {
        try {
            DirectorDto stored = directorFacade.findById(id);
            stored.setFirstName(!dto.getFirstName().equals("") ? dto.getFirstName() : stored.getFirstName());
            stored.setLastName(!dto.getLastName().equals("") ? dto.getLastName() : stored.getFirstName());

            return directorFacade.update(stored);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void delete(@PathVariable("id") Long id) throws Exception {
        try {
            DirectorDto stored = directorFacade.findById(id);
            directorFacade.delete(stored);
        } catch (Exception ex) {
            throw new Exception("does not exist");
        }
    }

}
