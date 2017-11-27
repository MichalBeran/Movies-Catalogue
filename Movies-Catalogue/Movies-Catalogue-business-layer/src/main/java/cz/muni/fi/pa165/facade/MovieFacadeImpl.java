/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.GenreService;
import cz.muni.fi.pa165.service.MovieService;
import cz.muni.fi.pa165.service.RatingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author Dominik
 */
@Transactional
@Service
public class MovieFacadeImpl implements MovieFacade{

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovieDto> getRecommendedMovies(MovieDto m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovieDto> getNewestMovies(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long createMovie(CreateMovieDto movie) {
        beanMappingService.mapTo(movie, Movie.class);
        return Long.valueOf(1);
    }

    @Override
    public String deleteMovie(Long movieId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
