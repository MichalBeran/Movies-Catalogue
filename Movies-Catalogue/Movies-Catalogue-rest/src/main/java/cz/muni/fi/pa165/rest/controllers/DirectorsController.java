package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.dto.detail.DirectorDetailDto;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.rest.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@RestController
@RequestMapping(Api.ROOT_URI_DIRECTORS)
public class DirectorsController {

    @Inject
    private DirectorFacade directorFacade;
    
    @Autowired
    private BeanMappingService mapper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DirectorDetailDto>> index() {
        return ResponseEntity.ok(mapper.mapTo(directorFacade.findAll(), DirectorDetailDto.class));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<DirectorDetailDto> create(@RequestBody DirectorDto dto) throws Exception {
        try {
            Long id = directorFacade.create(dto);
            return ResponseEntity.ok(mapper.mapTo(directorFacade.findById(id), DirectorDetailDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDetailDto> get(@PathVariable("id") Long id) throws Exception {
        try{
            DirectorDetailDto dto = mapper.mapTo(directorFacade.findById(id), DirectorDetailDto.class);
            return ResponseEntity.ok(dto);
        }catch(InvalidDataAccessApiUsageException e){
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<DirectorDetailDto> update(@PathVariable("id") Long id, @RequestBody DirectorDto dto) throws Exception {
        try {
            DirectorDto stored = directorFacade.findById(id);
            stored.setFirstName(!dto.getFirstName().equals("") ? dto.getFirstName() : stored.getFirstName());
            stored.setLastName(!dto.getLastName().equals("") ? dto.getLastName() : stored.getFirstName());
            stored.setDateOfBirth(!dto.getDateOfBirth().equals("") ? dto.getDateOfBirth() : stored.getDateOfBirth());


            return ResponseEntity.ok(mapper.mapTo(directorFacade.update(stored), DirectorDetailDto.class));
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
