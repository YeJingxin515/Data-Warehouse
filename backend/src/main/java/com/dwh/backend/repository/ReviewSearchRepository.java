package com.dwh.backend.repository;

import com.dwh.backend.model.ReviewSearch;
import com.dwh.backend.model.ReviewSearchId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewSearchRepository extends JpaRepository<ReviewSearch, ReviewSearchId> {
}