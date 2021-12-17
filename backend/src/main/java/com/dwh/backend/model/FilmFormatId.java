package com.dwh.backend.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmFormatId implements Serializable {
    private static final long serialVersionUID = 1431697487598998736L;
    @Column(name = "film_id", nullable = false)
    private Integer filmId;
    @Column(name = "format_id", nullable = false)
    private Integer formatId;

    public Integer getFormatId() {
        return formatId;
    }

    public void setFormatId(Integer formatId) {
        this.formatId = formatId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatId, filmId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmFormatId entity = (FilmFormatId) o;
        return Objects.equals(this.formatId, entity.formatId) &&
                Objects.equals(this.filmId, entity.filmId);
    }
}