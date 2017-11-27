package cz.muni.fi.pa165.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    private String description;

    private String image;

    @NotNull
    private LocalDate dateOfRelease;

    @ManyToMany(mappedBy = "movies", cascade=CascadeType.REMOVE)
    private Set<Genre> genres;

    @NotNull
    @ManyToMany(mappedBy = "movies", cascade=CascadeType.REMOVE)
    private Set<Actor> actors;

    @NotNull
    @ManyToOne(cascade=CascadeType.REMOVE)
    private Director director;
    
    @OneToOne
    private Rating rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    private Set<Rating> ratings;

    public Set<Rating> getRatings() {
        return ratings;
    }
    public void addRating(Rating rating){
        this.ratings.add(rating);
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
    

    public Movie(){
        this.actors = new HashSet<>();
        this.genres = new HashSet<>();
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre){ this.genres.add(genre); }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor actor){ this.actors.add(actor); }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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
