package cz.muni.fi.pa165.builders;

import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Genre;

import java.util.List;
import java.util.Set;

/**
 * @author Marek Urban
 */
public class GenreBuilder{

    private Genre genre;

    public GenreBuilder(){
        this.genre = new Genre();
    }

    public GenreBuilder id(Long id){
        this.genre.setId(id);
        return this;
    }

    public GenreBuilder name(String name){
        this.genre.setName(name);
        return this;
    }

    public GenreBuilder description(String description){
        this.genre.setDescription(description);
        return this;
    }

    public GenreBuilder movies(List<Movie> movies){
        this.genre.setMovies(movies);
        return this;
    }

    public Genre build(){
        Genre genre = new Genre();
        genre.setId(this.genre.getId());
        genre.setName(this.genre.getName());
        genre.setDescription(this.genre.getDescription());
        genre.setMovies(this.genre.getMovies());
        this.genre = new Genre();
        return genre;
    }
}
