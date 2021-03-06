package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_merged", schema = "data_warehouse", catalog = "")
public class FilmMerged {
    private int id;
    private String title;
    private String rating;
    private String releaseDate;
    private String producer;
    private String language;
    private String genre;
    private String formatNum;
    private Integer rate;
    private String dateFrom;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "rating")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "producer")
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "format_num")
    public String getFormatNum() {
        return formatNum;
    }

    public void setFormatNum(String formatNum) {
        this.formatNum = formatNum;
    }

    @Basic
    @Column(name = "rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "date_from")
    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmMerged that = (FilmMerged) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(rating, that.rating) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(producer, that.producer) && Objects.equals(language, that.language) && Objects.equals(genre, that.genre) && Objects.equals(formatNum, that.formatNum) && Objects.equals(rate, that.rate) && Objects.equals(dateFrom, that.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rating, releaseDate, producer, language, genre, formatNum, rate, dateFrom);
    }
}
