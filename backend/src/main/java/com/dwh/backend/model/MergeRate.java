package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "merge_rate", schema = "data_warehouse", catalog = "")
public class MergeRate {
    private int filmId;
    private String title;
    private Integer rate;

    @Id
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MergeRate mergeRate = (MergeRate) o;
        return filmId == mergeRate.filmId && Objects.equals(title, mergeRate.title) && Objects.equals(rate, mergeRate.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, rate);
    }
}
