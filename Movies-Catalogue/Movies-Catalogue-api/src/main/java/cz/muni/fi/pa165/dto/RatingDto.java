package cz.muni.fi.pa165.dto;

/**
 * @author Marek Urban
 */
public class RatingDto {
    private Long id;
    private String description;
    private int overallRating;
    private int scenarioRating;
    private int actorsRating;

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
}
