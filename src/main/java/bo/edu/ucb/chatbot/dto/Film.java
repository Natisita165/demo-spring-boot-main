package bo.edu.ucb.chatbot.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Film {
    private Integer filmId;
    private String title;
    private String description;
    private Short releaseYear;
    private String language;
    private String originalLanguage;
    private Integer length;
    private String lengthLabel;
    private String rating;
    private String specialFeatures;
    private List<String> actors;
    private String category;
    private Date lastUpdate;
    private Integer rental_duration;
    private Integer rental_rate;
    private String languageid;
    private String originalLanguageid;
    private Integer replacement_cost;


    public Film() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId.equals(film.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lengthLabel='" + lengthLabel + '\'' +
                '}';
    }

    public void setLengthLabel(String lengthLabel) {
        this.lengthLabel = lengthLabel;
    }

    public Integer getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(Integer rental_duration) {
        this.rental_duration = rental_duration;
    }

    public Integer getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(Integer rental_rate) {
        this.rental_rate = rental_rate;
    }

    public String getLanguageid() {
        return languageid;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    public String getOriginalLanguageid() {
        return originalLanguageid;
    }

    public void setOriginalLanguageid(String originalLanguageid) {
        this.originalLanguageid = originalLanguageid;
    }

    public Integer getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(Integer replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        if (length != null) { // Algoritmo para convertir a horas minutos
            int hours = length / 60;
            int minutes = length % 60;
            if (hours > 0) {
                lengthLabel = hours + "h " + minutes + "m";
            } else {
                lengthLabel = minutes + "m";
            }

        }
        this.length = length;
    }

    public String getLengthLabel() {
        return lengthLabel;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
