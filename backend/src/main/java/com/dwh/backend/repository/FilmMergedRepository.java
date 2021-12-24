package com.dwh.backend.repository;

import com.dwh.backend.model.FilmMerged;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmMergedRepository extends JpaRepository<FilmMerged, Integer> {

    List<FilmMerged> findByTitle(String name);
    List<FilmMerged> findByRate(Integer rate);

}