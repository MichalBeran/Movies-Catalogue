/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.builders;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.DirectorDto;
import cz.muni.fi.pa165.dto.GenreDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominik
 */
public class CreateMovieDtoBuilder {
    
    private String title;

    private String description;

    private String image;

    private LocalDate dateOfRelease;

    private List<GenreDto> genres;

    private List<ActorDto> actors;

    private DirectorDto director;
    
    public CreateMovieDtoBuilder(){
        this.genres = new ArrayList();
        this.actors = new ArrayList();
    }

    public CreateMovieDtoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public CreateMovieDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CreateMovieDtoBuilder image(String image) {
        this.image = image;
        return this;
    }

    public CreateMovieDtoBuilder dateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
        return this;
    }

    public CreateMovieDtoBuilder genre(GenreDto genre) {
        this.genres.add(genre);
        return this;
    }

    public CreateMovieDtoBuilder actor(ActorDto actor) {
        this.actors.add(actor);
        return this;
    }

    public CreateMovieDtoBuilder director(DirectorDto director) {
        this.director = director;
        return this;
    }
    
    public CreateMovieDto build(){
        CreateMovieDto result = new CreateMovieDto();
        result.setActors(actors);
        result.setDateOfRelease(dateOfRelease);
        result.setDescription(description);
        result.setDirector(director);
        result.setGenres(genres);
        result.setImage(image);
        result.setTitle(title);
        return result;
    }
    
}
