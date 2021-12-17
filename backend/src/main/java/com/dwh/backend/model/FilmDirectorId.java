package com.dwh.backend.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmDirectorId implements Serializable {
    private static final long serialVersionUID = 7968348614987080329L;
    @Column(name = "director_id", nullable = false)
    private Integer directorId;
    @Column(name = "film_id", nullable = false)
    private Integer filmId;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorId, filmId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmDirectorId entity = (FilmDirectorId) o;
        return Objects.equals(this.directorId, entity.directorId) &&
                Objects.equals(this.filmId, entity.filmId);
    }
}