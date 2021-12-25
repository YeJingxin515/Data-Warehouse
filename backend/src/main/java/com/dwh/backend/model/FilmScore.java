package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_score", schema = "data_warehouse", catalog = "")
@IdClass(FilmScorePK.class)
public class FilmScore {
    private int belong;
    private String filmName;
    private Double avgOverall;

    @Id
    @Column(name = "belong")
    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    @Id
    @Column(name = "film_name")
    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Basic
    @Column(name = "AVG(overall)")
    public Double getAvgOverall() {
        return avgOverall;
    }

    public void setAvgOverall(Double avgOverall) {
        this.avgOverall = avgOverall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmScore filmScore = (FilmScore) o;
        return belong == filmScore.belong && Objects.equals(filmName, filmScore.filmName) && Objects.equals(avgOverall, filmScore.avgOverall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(belong, filmName, avgOverall);
    }
}
