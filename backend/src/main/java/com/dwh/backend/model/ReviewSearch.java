package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "review_search")
@Entity
public class ReviewSearch {
    @EmbeddedId
    private ReviewSearchId id;

    @Column(name = "overall")
    private Double overall;

    @Column(name = "rate")
    private Integer rate;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Double getOverall() {
        return overall;
    }

    public void setOverall(Double overall) {
        this.overall = overall;
    }

    public ReviewSearchId getId() {
        return id;
    }

    public void setId(ReviewSearchId id) {
        this.id = id;
    }
}