/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominik
 */
public class CreateMovieDto {
    
    //@NotNull
    private String title;

    private String description;

    private String image;

    //@NotNull
    private LocalDate dateOfRelease;

    private List<GenreDto> genres;

    //@NotNull
    private List<ActorDto> actors;

    //@NotNull
    private DirectorDto director;

    
    public String getTitle() {
        return title;
    }

    public CreateMovieDto() {
        this.genres = new ArrayList();
        this.actors = new ArrayList();
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

    public DirectorDto getDirector() {
        return director;
    }

    public void setDirector(DirectorDto director) {
        this.director = director;
    }
    
    
}
