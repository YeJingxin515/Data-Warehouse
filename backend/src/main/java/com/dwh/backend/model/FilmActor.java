package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_actor", schema = "data_warehouse", catalog = "")
@IdClass(FilmActorPK.class)
public class FilmActor {
    private int actorId;
    private int filmId;
    private String actorName;
    private String filmName;

    @Id
    @Column(name = "actor_id")
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
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
    @Column(name = "actor_name")
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
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
        FilmActor filmActor = (FilmActor) o;
        return actorId == filmActor.actorId && filmId == filmActor.filmId && Objects.equals(actorName, filmActor.actorName) && Objects.equals(filmName, filmActor.filmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId, actorName, filmName);
    }
}
