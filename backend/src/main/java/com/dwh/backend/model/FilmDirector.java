package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_director", schema = "data_warehouse", catalog = "")
@IdClass(FilmDirectorPK.class)
public class FilmDirector {
    private int directorId;
    private int filmId;
    private String directorName;
    private String filmName;

    @Id
    @Column(name = "director_id")
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Id
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "director_name")
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Basic
    @Column(name = "film_name")
    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDirector that = (FilmDirector) o;
        return directorId == that.directorId && filmId == that.filmId && Objects.equals(directorName, that.directorName) && Objects.equals(filmName, that.filmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorId, filmId, directorName, filmName);
    }
}
