package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(Api.ROOT_URI_ACTORS)
public class ActorsController {

    final static Logger logger = LoggerFactory.getLogger(ActorsController.class);

    @Inject
    private ActorFacade actorFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActorDto>> index() {
        return ResponseEntity.ok().body(actorFacade.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<ActorDto> create(@RequestBody ActorDto dto) throws Exception {
        try {
            Long id = actorFacade.create(dto);
            return ResponseEntity.ok(actorFacade.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActorDto> get(@PathVariable("id") Long id) throws Exception {
        ActorDto dto = actorFacade.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<ActorDto> update(@PathVariable("id") Long id, @RequestBody ActorDto dto) throws Exception {
        try {
            ActorDto stored = actorFacade.findById(id);
            stored.setFirstName(!dto.getLastName().equals("") ? dto.getFirstName() : stored.getLastName());
            stored.setLastName(!dto.getLastName().equals("") ? dto.getLastName() : stored.getLastName());

            return ResponseEntity.ok(actorFacade.update(stored));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            ActorDto stored = actorFacade.findById(id);
            actorFacade.delete(stored);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
