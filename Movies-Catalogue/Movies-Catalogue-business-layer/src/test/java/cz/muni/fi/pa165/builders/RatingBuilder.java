package cz.muni.fi.pa165.builders;

import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;

public class RatingBuilder {

    private Rating rating;

    public RatingBuilder(){
        this.rating = new Rating();
    }

    public RatingBuilder id(Long id){
        this.rating.setId(id);
        return this;
    }

    public RatingBuilder movie(Movie movie){
        this.rating.setMovie(movie);
        return this;
    }

    public RatingBuilder actorRtg(int score){
        this.rating.setActorsRating(score);
        return this;
    }
    public RatingBuilder scenarioRtg(int score){
        this.rating.setScenarioRating(score);
        return this;
    }
    public RatingBuilder overallRtg(int score){
        this.rating.setOverallRating(score);
        return this;
    }

    public Rating build(){
        Rating rating = new Rating();
        rating.setId(this.rating.getId());
        rating.setMovie(this.rating.getMovie());
        rating.setActorsRating(this.rating.getActorsRating());
        rating.setScenarioRating(this.rating.getScenarioRating());
        rating.setOverallRating(this.rating.getOverallRating());
        this.rating = new Rating();
        return rating;
    }
}
