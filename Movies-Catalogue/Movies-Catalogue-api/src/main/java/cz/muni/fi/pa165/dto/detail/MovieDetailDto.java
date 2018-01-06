package cz.muni.fi.pa165.dto.detail;

import cz.muni.fi.pa165.dto.view.ActorViewDto;
import cz.muni.fi.pa165.dto.view.GenreViewDto;
import cz.muni.fi.pa165.dto.view.DirectorViewDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class MovieDetailDto {
    
    private Long id;
    
    private String title;

    private String description;

    private String image;

    private String date;

    private LocalDate dateOfRelease;

    private List<GenreViewDto> genres;

    private List<ActorViewDto> actors;

    private DirectorViewDto director;

    private int overallRating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<GenreViewDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreViewDto> genres) {
        this.genres = genres;
    }

    public List<ActorViewDto> getActors() {
        return actors;
    }

    public void setActors(List<ActorViewDto> actors) {
        this.actors = actors;
    }

    public DirectorViewDto getDirector() {
        return director;
    }

    public void setDirector(DirectorViewDto director) {
        this.director = director;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }
}
