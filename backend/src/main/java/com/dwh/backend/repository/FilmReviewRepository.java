package com.dwh.backend.repository;

import com.dwh.backend.model.FilmReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmReviewRepository extends JpaRepository<FilmReview, Integer> {
}