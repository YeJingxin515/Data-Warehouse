package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "film_director")
@Entity
public class FilmDirector {
    @EmbeddedId
    private FilmDirectorId id;

    @Column(name = "director_name", length = 50)
    private String directorName;

    @Column(name = "film_name", length = 50)
    private String filmName;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public FilmDirectorId getId() {
        return id;
    }

    public void setId(FilmDirectorId id) {
        this.id = id;
    }
}