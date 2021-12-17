package com.dwh.backend.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewSearchId implements Serializable {
    private static final long serialVersionUID = 6628720809431367234L;
    @Column(name = "reviewer_id", nullable = false, length = 20)
    private String reviewerId;
    @Column(name = "asin", nullable = false, length = 20)
    private String asin;

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewerId, asin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviewSearchId entity = (ReviewSearchId) o;
        return Objects.equals(this.reviewerId, entity.reviewerId) &&
                Objects.equals(this.asin, entity.asin);
    }
}