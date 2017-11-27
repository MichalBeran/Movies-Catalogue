/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Dominik
 */
public class CreateMovieDTO {
    
    @NotNull
    private String title;

    private String description;

    private String image;

    @NotNull
    private LocalDate dateOfRelease;

    private List<GenreDto> genres = new ArrayList();

    @NotNull
    private List<ActorDto> actors = new ArrayList();

    @NotNull
    private DirectorDto director;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public List<ActorDto> getActors() {
        return actors;
    }

    public void setActors(List<ActorDto> actors) {
        this.actors = actors;
    }

    public DirectorDto getDirector() {
        return director;
    }

    public void setDirector(DirectorDto director) {
        this.director = director;
    }
    
    
}
