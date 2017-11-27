package cz.muni.fi.pa165.builders;

import java.time.LocalDate;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Actor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Maros on 11/27/2017.
 */
public class ActorDtoBuilder {

    private ActorDto actor;

    public ActorDtoBuilder(){
        this.actor = new ActorDto();
    }

    public ActorDtoBuilder id(Long id){
        this.actor.setId(id);
        return this;
    }

    public ActorDtoBuilder firstName(String firstNameame){
        this.actor.setFirstName(firstNameame);
        return this;
    }

    public ActorDtoBuilder lastName(String lastNameame){
        this.actor.setLastName(lastNameame);
        return this;
    }

    public ActorDtoBuilder dateOfBirth(LocalDate dateOfBirth){
        this.actor.setDateOfBirth(dateOfBirth);
        return this;
    }

    public ActorDtoBuilder movies(List<MovieDto> movies){
        this.actor.setMovies((Set<MovieDto>)movies);
        return this;
    }

    public ActorDto build(){
        ActorDto actor = new ActorDto();
        actor.setId(this.actor.getId());
        actor.setFirstName(this.actor.getFirstName());
        actor.setDateOfBirth(this.actor.getDateOfBirth());
        actor.setMovies(this.actor.getMovies());
        this.actor = new ActorDto();
        return actor;
    }
}
