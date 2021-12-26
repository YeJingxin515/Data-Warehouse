package com.dwh.backend.repository;

import com.dwh.backend.model.FilmRate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRateRepository extends JpaRepository<FilmRate, Integer> {

    List<FilmRate> findByOverall(Double overall);
    List<FilmRate> findByRate(Integer rate);
}