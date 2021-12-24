package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FilmDirectorPK implements Serializable {
    private int directorId;
    private int filmId;

    @Column(name = "director_id")
    @Id
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Column(name = "film_id")
    @Id
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDirectorPK that = (FilmDirectorPK) o;
        return directorId == that.directorId && filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorId, filmId);
    }
}
