package cz.muni.fi.pa165.dto;

import java.util.Objects;

/**
 * @author Marek Urban
 */
public class RatingDto {
    private Long id;
    private String description;
    private int overallRating;
    private int scenarioRating;
    private int actorsRating;
    private MovieDto movie;
    private UserDto user;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public int getScenarioRating() {
        return scenarioRating;
    }

    public void setScenarioRating(int scenarioRating) {
        this.scenarioRating = scenarioRating;
    }

    public int getActorsRating() {
        return actorsRating;
    }

    public void setActorsRating(int actorsRating) {
        this.actorsRating = actorsRating;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rating {" + "id=" + id + ", overallRating=" + overallRating + ", scenarioRating=" + scenarioRating + ",actorsRating=" + actorsRating +'}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + overallRating;
        result = prime * result + scenarioRating;
        result = prime * result + actorsRating;
        result = prime * result + movie.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RatingDto))
            return false;
        final RatingDto other = (RatingDto) obj;

        return Objects.equals(overallRating, other.getOverallRating())
                && Objects.equals(scenarioRating, other.getScenarioRating())
                && Objects.equals(actorsRating, other.getActorsRating())
                && Objects.equals(movie, other.getMovie());
    }
}
