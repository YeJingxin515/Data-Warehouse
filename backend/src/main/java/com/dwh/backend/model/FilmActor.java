package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "film_actor")
@Entity
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;

    @Column(name = "actor_name", length = 50)
    private String actorName;

    @Column(name = "film_name", length = 50)
    private String filmName;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }
}