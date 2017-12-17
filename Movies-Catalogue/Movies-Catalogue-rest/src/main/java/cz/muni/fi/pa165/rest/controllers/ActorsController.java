package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(Api.ROOT_URI_ACTORS)
public class ActorsController {

    final static Logger logger = LoggerFactory.getLogger(GenresController.class);

    //TODO: test for bugs, certainly the methods will throw some exceptions for null pointers

    @Inject
    private ActorFacade actorFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActorDto> index() {
        return actorFacade.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ActorDto create(@RequestBody ActorDto dto) throws Exception {
        try {
            logger.debug("dto incoming: "+dto.toString());
            Long id = actorFacade.create(dto);
            return actorFacade.findById(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActorDto get(@PathVariable("id") Long id) throws Exception {
        ActorDto dto = actorFacade.findById(id);
        if (dto == null) {
            // TODO: add exceptions to the project
            throw new Exception("not found");
        }
        return dto;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ActorDto update(@PathVariable("id") Long id, @RequestBody ActorDto dto) throws Exception {
        try {
            ActorDto stored = actorFacade.findById(id);
            stored.setFirstName(!dto.getFirstName().equals("") ? dto.getFirstName() : stored.getFirstName());
            stored.setLastName(!dto.getLastName().equals("") ? dto.getLastName() : stored.getFirstName());

            return actorFacade.update(stored);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void delete(@PathVariable("id") Long id) throws Exception {
        try {
            ActorDto stored = actorFacade.findById(id);
            actorFacade.delete(stored);
        } catch (Exception ex) {
            throw new Exception("does not exist");
        }
    }

}
