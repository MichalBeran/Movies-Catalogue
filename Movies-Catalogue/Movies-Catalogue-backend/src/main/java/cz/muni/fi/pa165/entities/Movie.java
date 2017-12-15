package cz.muni.fi.pa165.entities;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 * @author Marek Urban
 */
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Length(max = 600)
    private String description;

    @Type(type="text")
    private String image;

    @NotNull
    private LocalDate dateOfRelease;

    @ManyToMany(mappedBy = "movies", cascade=CascadeType.REMOVE)
    private List<Genre> genres;

    @NotNull
    @ManyToMany(mappedBy = "movies", cascade=CascadeType.REMOVE)
    private List<Actor> actors;

    @NotNull
    @ManyToOne(cascade=CascadeType.REMOVE)
    private Director director;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    private List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }
    public void addRating(Rating rating){
        this.ratings.add(rating);
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    

    public Movie(){
        this.actors = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }
    
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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre){ this.genres.add(genre); }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor actor){ this.actors.add(actor); }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", description=" + description + ", dateOfRelease=" + dateOfRelease.toString() + '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((dateOfRelease == null) ? 0 : dateOfRelease.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Movie))
            return false;
        final Movie other = (Movie) obj;

        return Objects.equals(title, other.getTitle())
                && Objects.equals(dateOfRelease, other.getDateOfRelease());
    }


}
