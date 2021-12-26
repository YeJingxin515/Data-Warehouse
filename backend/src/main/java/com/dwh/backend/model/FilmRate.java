package com.dwh.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_rate", schema = "data_warehouse", catalog = "")
public class FilmRate {
    private int id;
    private String title;
    private Integer rate;
    private Double overall;
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
    @Column(name = "rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
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
        FilmRate filmRate = (FilmRate) o;
        return id == filmRate.id && Objects.equals(title, filmRate.title) && Objects.equals(rate, filmRate.rate) && Objects.equals(overall, filmRate.overall) && Objects.equals(dateFrom, filmRate.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rate, overall, dateFrom);
    }
}
