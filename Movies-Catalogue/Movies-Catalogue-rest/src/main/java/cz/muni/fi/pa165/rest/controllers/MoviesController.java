package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.detail.MovieDetailDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(Api.ROOT_URI_MOVIES)
public class MoviesController {

    final static Logger logger = LoggerFactory.getLogger(MoviesController.class);

    //TODO: test for bugs, certainly the methods will throw some exceptions for null pointers

    @Autowired
    private BeanMappingService mapper;

    @Inject
    private MovieFacade movieFacade;

    @Inject
    private GenreFacade genreFacade;

    @Inject
    private ActorFacade actorFacade;

    @Inject
    private DirectorFacade directorFacade;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDetailDto>> index() {
        return ResponseEntity.ok(mapper.mapTo(movieFacade.findAll(), MovieDetailDto.class));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<MovieDetailDto> create(@RequestBody CreateMovieDto dto) throws Exception {
        try {
            List<GenreDto> genres = new ArrayList<>();
            for (GenreDto genre: dto.getGenres()){
                genres.add(genreFacade.findById(genre.getId()));
            }
            dto.setGenres(genres);
            List<ActorDto> actors = new ArrayList<>();
            for (ActorDto actor: dto.getActors()){
                actors.add(actorFacade.findById(actor.getId()));
            }
            dto.setActors(actors);
            dto.setDirector(directorFacade.findById(dto.getDirector().getId()));

            LocalDate ld = LocalDate.parse(dto.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dto.setDateOfRelease(ld);
            Long id = movieFacade.createMovie(dto);
            return ResponseEntity.ok(mapper.mapTo(movieFacade.findById(id), MovieDetailDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDetailDto> get(@PathVariable("id") Long id) throws Exception {
        try{
            MovieDto dto = movieFacade.findById(id);
            return ResponseEntity.ok(mapper.mapTo(dto, MovieDetailDto.class));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<MovieDetailDto> update(@PathVariable("id") Long id, @RequestBody MovieDto dto) throws Exception {
        try {
            MovieDto stored = movieFacade.findById(id);
            stored.setDescription(!dto.getDescription().equals("") ? dto.getDescription() : stored.getDescription());
            stored.setTitle(!dto.getTitle().equals("") ? dto.getTitle() : stored.getTitle());
            stored.setImage(!dto.getImage().equals("") ? dto.getImage() : stored.getImage());
            stored.setDirector(!dto.getDirector().equals("") ? dto.getDirector() : stored.getDirector());
            stored.setActors(!dto.getActors().equals("") ? dto.getActors() : stored.getActors());
            stored.setGenres(!dto.getGenres().equals("") ? dto.getGenres() : stored.getGenres());
            stored.setDateOfRelease(!dto.getDateOfRelease().equals("") ? dto.getDateOfRelease() : stored.getDateOfRelease());

            return ResponseEntity.ok(mapper.mapTo(movieFacade.update(stored), MovieDetailDto.class));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            MovieDto stored = movieFacade.findById(id);
            movieFacade.delete(stored);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
