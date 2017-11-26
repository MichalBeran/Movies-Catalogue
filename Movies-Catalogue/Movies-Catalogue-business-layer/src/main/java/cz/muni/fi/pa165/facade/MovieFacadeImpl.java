/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CreateMovieDTO;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.GenreService;
import cz.muni.fi.pa165.service.MovieService;
import cz.muni.fi.pa165.service.RatingService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dominik
 */
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
    public List<Movie> getRecommendedMovies(Movie m) {
        Set<Genre> genresOfFilm = m.getGenres();
        Iterator itr = genresOfFilm.iterator();
        if(itr.hasNext()){
            Genre genreOfFilm = genreService.findById(((Genre) itr.next()).getId());
            Set<Movie> recommendedMovies = genreOfFilm.getMovies();
            return new ArrayList<>(recommendedMovies);
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Movie> getTopMovies(Genre g) {
        Iterator itr = g.getMovies().iterator();
        List<Movie> topMovies = new ArrayList<>();
        while(itr.hasNext()){
            Movie movie = (Movie) itr.next();
            //Rating overallRating = ratingService.getOverallRating();
            //if(overallRating.getOverallRating() > 4){
            //    topMovies.add(movie);
            //}
        }
        return topMovies;
    }

    @Override
    public void createMovie(CreateMovieDTO movie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMovie(Long movieId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
