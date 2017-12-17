package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.GenreDto;
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
@RequestMapping(Api.ROOT_URI_GENRES)
public class GenresController {

    final static Logger logger = LoggerFactory.getLogger(GenresController.class);

    @Inject
    private GenreFacade genreFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenreDto>> index() {
        return ResponseEntity.ok().body(genreFacade.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<GenreDto> create(@RequestBody GenreDto dto) throws Exception {
        try {
            Long id = genreFacade.create(dto);
            return ResponseEntity.ok(genreFacade.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreDto> get(@PathVariable("id") Long id) throws Exception {
        GenreDto dto = genreFacade.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<GenreDto> update(@PathVariable("id") Long id, @RequestBody GenreDto dto) throws Exception {
        try {
            GenreDto stored = genreFacade.findById(id);
            stored.setDescription(!dto.getDescription().equals("") ? dto.getDescription() : stored.getDescription());
            stored.setName(!dto.getName().equals("") ? dto.getName() : stored.getName());

            return ResponseEntity.ok(genreFacade.update(stored));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            GenreDto stored = genreFacade.findById(id);
            genreFacade.delete(stored);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
