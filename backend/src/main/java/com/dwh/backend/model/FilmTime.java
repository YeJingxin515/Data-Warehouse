package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "film_time")
@Entity
public class FilmTime {
    @Id
    @Column(name = "film_id", nullable = false)
    private Integer id;

    @Column(name = "film_title")
    private String filmTitle;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month", length = 10)
    private String month;

    @Column(name = "date")
    private Integer date;

    @Column(name = "quarter")
    private Integer quarter;

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}