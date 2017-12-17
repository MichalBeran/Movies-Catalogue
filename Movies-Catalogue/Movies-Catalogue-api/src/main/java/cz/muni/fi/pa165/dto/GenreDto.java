package cz.muni.fi.pa165.dto;

import java.util.List;
import java.util.Objects;

/**
 * @author Marek Urban
 */
public class GenreDto {

    private Long id;
    private String name;
    private String description;
    private List<MovieDto> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Genre {" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GenreDto))
            return false;
        final GenreDto other = (GenreDto) obj;

        return Objects.equals(name, other.getName());
    }
}
