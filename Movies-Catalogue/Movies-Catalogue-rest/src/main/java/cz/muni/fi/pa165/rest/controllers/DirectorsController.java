package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.rest.Api;
import javassist.tools.web.BadHttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(Api.ROOT_URI_DIRECTORS)
public class DirectorsController {

    @Inject
    private DirectorFacade directorFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DirectorDto>> index() {
        return ResponseEntity.ok(directorFacade.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<DirectorDto> create(@RequestBody DirectorDto dto) throws Exception {
        try {
            Long id = directorFacade.create(dto);
            return ResponseEntity.ok(directorFacade.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDto> get(@PathVariable("id") Long id) throws Exception {
        DirectorDto dto = directorFacade.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<DirectorDto> update(@PathVariable("id") Long id, @RequestBody DirectorDto dto) throws Exception {
        try {
            if(!dto.getId().equals(id)){
                throw new Exception("Ids don't match.");
            }
            return ResponseEntity.ok(directorFacade.update(dto));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            DirectorDto stored = directorFacade.findById(id);
            directorFacade.delete(stored);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
