package cz.muni.fi.pa165.builders;

import java.time.LocalDate;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Actor;

import java.util.List;

/**
 * Created by Maros on 11/27/2017.
 */
public class ActorBuilder {


    private Actor actor;

    public ActorBuilder(){
        this.actor = new Actor();
    }

    public ActorBuilder id(Long id){
        this.actor.setId(id);
        return this;
    }

    public ActorBuilder firstName(String firstNameame){
        this.actor.setFirstName(firstNameame);
        return this;
    }

    public ActorBuilder lastName(String lastNameame){
        this.actor.setLastName(lastNameame);
        return this;
    }

    public ActorBuilder dateOfBirth(LocalDate dateOfBirth){
        this.actor.setDateOfBirth(dateOfBirth);
        return this;
    }

    public ActorBuilder movies(List<Movie> movies){
        this.actor.setMovies(movies);
        return this;
    }

    public Actor build(){
        Actor actor = new Actor();
        actor.setId(this.actor.getId());
        actor.setFirstName(this.actor.getFirstName());
        actor.setDateOfBirth(this.actor.getDateOfBirth());
        actor.setMovies(this.actor.getMovies());
        this.actor = new Actor();
        return actor;
    }
}
