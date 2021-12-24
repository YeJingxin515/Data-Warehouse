package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_time", schema = "data_warehouse", catalog = "")
public class FilmTime {
    private int filmId;
    private String filmTitle;
    private Integer year;
    private Integer month;
    private Integer date;
    private Integer quarter;

    @Id
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "film_title")
    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month")
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Basic
    @Column(name = "date")
    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Basic
    @Column(name = "quarter")
    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmTime filmTime = (FilmTime) o;
        return filmId == filmTime.filmId && Objects.equals(filmTitle, filmTime.filmTitle) && Objects.equals(year, filmTime.year) && Objects.equals(month, filmTime.month) && Objects.equals(date, filmTime.date) && Objects.equals(quarter, filmTime.quarter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, filmTitle, year, month, date, quarter);
    }
}
