package cz.muni.fi.pa165.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

//    TODO: Uncomment after implementing entity Genre
//    @OneToMany
//    private Set<Genre> genres;

    @NotNull
    @OneToMany
    private Set<Actor> actors;

    @NotNull
    @ManyToOne
    private Director director;

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

//    TODO: Uncomment after implementing entity Genre
//    public Set<Genre> getGenres() {
//        return genres;
//    }

//    TODO: Uncomment after implementing entity Genre
//    public void setGenres(Set<Genre> genres) {
//        this.genres = genres;
//    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        final int prime = 47;
        return prime * title.hashCode() * dateOfRelease.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Movie))
            return false;
        Movie other = (Movie) obj;

        return Objects.equals(title, other.getTitle())
                && Objects.equals(dateOfRelease, other.getDateOfRelease());
    }
}