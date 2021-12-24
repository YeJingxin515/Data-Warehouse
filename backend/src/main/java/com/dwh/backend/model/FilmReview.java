package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_review", schema = "data_warehouse", catalog = "")
public class FilmReview {
    private int id;
    private String reviewerId;
    private String reviewerName;
    private String asin;
    private String filmName;
    private Double overall;
    private String time;
    private String summary;
    private Integer rate;
    private Integer belong;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reviewer_id")
    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Basic
    @Column(name = "reviewer_name")
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    @Basic
    @Column(name = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Basic
    @Column(name = "film_name")
    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Basic
    @Column(name = "overall")
    public Double getOverall() {
        return overall;
    }

    public void setOverall(Double overall) {
        this.overall = overall;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
    @Column(name = "belong")
    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmReview that = (FilmReview) o;
        return id == that.id && Objects.equals(reviewerId, that.reviewerId) && Objects.equals(reviewerName, that.reviewerName) && Objects.equals(asin, that.asin) && Objects.equals(filmName, that.filmName) && Objects.equals(overall, that.overall) && Objects.equals(time, that.time) && Objects.equals(summary, that.summary) && Objects.equals(rate, that.rate) && Objects.equals(belong, that.belong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewerId, reviewerName, asin, filmName, overall, time, summary, rate, belong);
    }
}
