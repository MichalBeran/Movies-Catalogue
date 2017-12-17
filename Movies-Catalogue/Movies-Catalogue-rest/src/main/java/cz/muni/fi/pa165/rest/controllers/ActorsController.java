package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.detail.ActorDetailDto;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.rest.Api;
import java.awt.image.RescaleOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(Api.ROOT_URI_ACTORS)
public class ActorsController {

    final static Logger logger = LoggerFactory.getLogger(ActorsController.class);

    //TODO: test for bugs, certainly the methods will throw some exceptions for null pointers

    @Inject
    private ActorFacade actorFacade;
    @Autowired
    private BeanMappingService mapper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActorDetailDto>> index() {
        return ResponseEntity.ok(mapper.mapTo(actorFacade.findAll(), ActorDetailDto.class));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<ActorDetailDto> create(@RequestBody ActorDto dto) throws Exception {
        try {
            logger.debug("dto incoming: "+dto.toString());
            Long id = actorFacade.create(dto);
            return ResponseEntity.ok(mapper.mapTo(actorFacade.findById(id), ActorDetailDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActorDetailDto> get(@PathVariable("id") Long id) throws Exception {
        ActorDetailDto dto = mapper.mapTo(actorFacade.findById(id), ActorDetailDto.class);
        if (dto == null) {
            // TODO: add exceptions to the project
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<ActorDetailDto> update(@PathVariable("id") Long id, @RequestBody ActorDto dto) throws Exception {
        try {
            ActorDto stored = actorFacade.findById(id);
            stored.setFirstName(!dto.getFirstName().equals("") ? dto.getFirstName() : stored.getFirstName());
            stored.setLastName(!dto.getLastName().equals("") ? dto.getLastName() : stored.getFirstName());

            return ResponseEntity.ok(mapper.mapTo(actorFacade.update(stored), ActorDetailDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            ActorDto stored = actorFacade.findById(id);
            actorFacade.delete(stored);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
