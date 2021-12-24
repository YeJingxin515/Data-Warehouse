package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FilmGenrePK implements Serializable {
    private int genreId;
    private int filmId;

    @Column(name = "genre_id")
    @Id
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
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
        FilmGenrePK that = (FilmGenrePK) o;
        return genreId == that.genreId && filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, filmId);
    }
}
