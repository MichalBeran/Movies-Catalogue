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
import cz.muni.fi.pa165.service.GenreService;
import cz.muni.fi.pa165.service.MovieService;
import cz.muni.fi.pa165.service.RatingService;

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

}


