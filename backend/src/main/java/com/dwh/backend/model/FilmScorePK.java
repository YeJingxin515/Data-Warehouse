package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FilmScorePK implements Serializable {
    private int belong;
    private String filmName;

    @Column(name = "belong")
    @Id
    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    @Column(name = "film_name")
    @Id
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
        FilmScorePK that = (FilmScorePK) o;
        return belong == that.belong && Objects.equals(filmName, that.filmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(belong, filmName);
    }
}
