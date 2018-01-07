/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Dominik
 */
@Service
@Transactional
public class MovieFacadeImpl implements MovieFacade {

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<MovieDto> getTopMovies(GenreDto g) {
        List<Movie> foundMovies = movieService.getTopMovies(beanMappingService.mapTo(g, Genre.class));
        return beanMappingService.mapTo(foundMovies, MovieDto.class);
    }

    @Override
    public List<MovieDto> getRecommendedMovies(MovieDto m) {
        List<Movie> foundMovies = movieService.getRecommendedMovies(beanMappingService.mapTo(m,Movie.class));
        return beanMappingService.mapTo(foundMovies, MovieDto.class);
    }

    @Override
    public List<MovieDto> getNewestMovies(int i) {
        return beanMappingService.mapTo(movieService.getNewestMovies(i), MovieDto.class);
    }

    @Override
    public Long createMovie(CreateMovieDto movie) {
        Movie m = beanMappingService.mapTo(movie, Movie.class);
        movieService.create(m);
        return m.getId();
    }

    @Override
    public String deleteMovie(Long movieId) throws IllegalArgumentException{
        Movie m = movieService.findById(movieId);
        String name = m.getTitle();
        movieService.delete(movieId);
        return name;
    }

    @Override
    public MovieDto findById(Long id) {
        return beanMappingService.mapTo(movieService.findById(id), MovieDto.class);
    }

    @Override
    public void delete(MovieDto dto) {
        movieService.delete(dto.getId());
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieService.getAllMovies();
        return beanMappingService.mapTo(movies, MovieDto.class);
    }

    @Override
    public MovieDto update(MovieDto dto) {
        movieService.edit(beanMappingService.mapTo(dto, Movie.class));
        return beanMappingService.mapTo(movieService.findById(dto.getId()), MovieDto.class);
    }

    @Override
    public int getMovieOverallRating(MovieDto movie) {
        return movieService.getMovieOverallRating(beanMappingService.mapTo(movie, Movie.class));
    }

    @Override
    public List<MovieDto> findAllByTitle(String serachString) {
        return beanMappingService.mapTo(movieService.findAllByTitle(serachString), MovieDto.class);
    }

    @Override
    public List<MovieDto> findAllByActor(Long actorId) {
        return beanMappingService.mapTo(actorService.getAllMoviesForActor(actorId), MovieDto.class);
    }

    @Override
    public List<MovieDto> findAllByDirector(Long directorId) {
        return beanMappingService.mapTo(directorService.getAllMoviesForDirector(directorId), MovieDto.class);
    }
}


