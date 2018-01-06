package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.dto.detail.MovieDetailDto;
import cz.muni.fi.pa165.dto.view.MovieViewDto;
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
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
        List<MovieDetailDto> list = mapper.mapTo(movieFacade.findAll(), MovieDetailDto.class);
        for(int i = 0; i<list.size(); i++){
            MovieDetailDto movie = list.get(i);
            movie.setOverallRating(movieFacade.getMovieOverallRating(mapper.mapTo(movie, MovieDto.class)));
            list.set(i, movie);
        }
        return ResponseEntity.ok(list);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<MovieDetailDto> create(@RequestBody CreateMovieDto dto) throws Exception {
        try {
            List<GenreDto> genres = new ArrayList<>();
            if(dto.getGenres() != null) {
                for (GenreDto genre : dto.getGenres()) {
                    if(genre.getId() != null) {
                        genres.add(genreFacade.findById(genre.getId()));
                    }
                }
            }
            if(genres.size() < 1) {
                genres.add(genreFacade.findById(1l));
            }
            dto.setGenres(genres);
            List<ActorDto> actors = new ArrayList<>();
            if(dto.getActors()!= null) {
                for (ActorDto actor : dto.getActors()) {
                    if(actor.getId() !=null) {
                        actors.add(actorFacade.findById(actor.getId()));
                    }
                }
            }
            if(actors.size() < 1) {
                actors.add(actorFacade.findById(1l));
            }
            dto.setActors(actors);
            if(dto.getDirector().getId() != null){
                dto.setDirector(directorFacade.findById(dto.getDirector().getId()));
            }else{
                dto.setDirector(directorFacade.findById(1l));
            }
            LocalDate ld = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
            dto.setDate(dto.getDateOfRelease().toString());
            MovieDetailDto detailDto = mapper.mapTo(dto, MovieDetailDto.class);
            detailDto.setOverallRating(movieFacade.getMovieOverallRating(dto));
            return ResponseEntity.ok(detailDto);
        }catch(InvalidDataAccessApiUsageException e){
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<MovieDetailDto> update(@PathVariable("id") Long id, @RequestBody MovieDto dto) throws Exception {
        try {
            List<GenreDto> genres = new ArrayList<>();
            if(dto.getGenres() != null) {
                for (GenreDto genre : dto.getGenres()) {
                    if(genre.getId() != null) {
                        genres.add(genreFacade.findById(genre.getId()));
                    }
                }
            }
            if(genres.size() < 1) {
                genres.add(genreFacade.findById(1l));
            }
            dto.setGenres(genres);
            List<ActorDto> actors = new ArrayList<>();
            if(dto.getActors()!= null) {
                for (ActorDto actor : dto.getActors()) {
                    if(actor.getId() !=null) {
                        actors.add(actorFacade.findById(actor.getId()));
                    }
                }
            }
            if(actors.size() < 1) {
                actors.add(actorFacade.findById(1l));
            }
            dto.setActors(actors);
            if(dto.getDirector().getId() != null){
                dto.setDirector(directorFacade.findById(dto.getDirector().getId()));
            }else{
                dto.setDirector(directorFacade.findById(1l));
            }
            LocalDate ld = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dto.setDateOfRelease(ld);

            return ResponseEntity.ok(mapper.mapTo(movieFacade.update(dto), MovieDetailDto.class));
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
    
    @RequestMapping(value = "recom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<List<MovieDetailDto>> getReccomendedMovies(@PathVariable("id") Long id) throws Exception{
        try{
            MovieDto model = movieFacade.findById(id);
            List<MovieDto> candidates = movieFacade.getRecommendedMovies(model);
            return ResponseEntity.ok(mapper.mapTo(candidates, MovieDetailDto.class));
        }catch(InvalidDataAccessApiUsageException e){
            return ResponseEntity.notFound().build();
        }
    }

}
