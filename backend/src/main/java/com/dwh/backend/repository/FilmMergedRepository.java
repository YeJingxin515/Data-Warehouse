package com.dwh.backend.repository;

import com.dwh.backend.model.FilmMerged;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmMergedRepository extends JpaRepository<FilmMerged, Integer> {
}