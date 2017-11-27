package cz.muni.fi.pa165.builders;

import java.time.LocalDate;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Director;

import java.util.List;
import java.util.Set;

/**
 * Created by Maros on 11/27/2017.
 */
public class DirectorBuilder {


    private Director director;

    public DirectorBuilder(){
        this.director = new Director();
    }

    public DirectorBuilder id(Long id){
        this.director.setId(id);
        return this;
    }

    public DirectorBuilder firstName(String firstNameame){
        this.director.setFirstName(firstNameame);
        return this;
    }

    public DirectorBuilder lastName(String lastNameame){
        this.director.setLastName(lastNameame);
        return this;
    }

    public DirectorBuilder description(LocalDate dateOfBirth){
        this.director.setDateOfBirth(dateOfBirth);
        return this;
    }

    public DirectorBuilder movies(List<Movie> movies){
        this.director.setMovies(movies);
        return this;
    }

    public Director build(){
        Director director = new Director();
        director.setId(this.director.getId());
        director.setFirstName(this.director.getFirstName());
        director.setDateOfBirth(this.director.getDateOfBirth());
        director.setMovies(this.director.getMovies());
        this.director = new Director();
        return director;
    }
}
