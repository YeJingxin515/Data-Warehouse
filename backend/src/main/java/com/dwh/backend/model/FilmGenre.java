package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_genre", schema = "data_warehouse", catalog = "")
@IdClass(FilmGenrePK.class)
public class FilmGenre {
    private int genreId;
    private int filmId;
    private String genreName;
    private String filmName;

    @Id
    @Column(name = "genre_id")
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
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
    @Column(name = "genre_name")
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
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
        FilmGenre filmGenre = (FilmGenre) o;
        return genreId == filmGenre.genreId && filmId == filmGenre.filmId && Objects.equals(genreName, filmGenre.genreName) && Objects.equals(filmName, filmGenre.filmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, filmId, genreName, filmName);
    }
}
