package com.dwh.backend.repository;

import com.dwh.backend.model.FilmTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmTimeRepository extends JpaRepository<FilmTime, Integer> {
    List<FilmTime> findByYear(Integer year);
    List<FilmTime> findByYearAndMonth(Integer year,Integer month);
    List<FilmTime> findByYearAndQuarter(Integer year,Integer quarter);
    List<FilmTime> findByYearAndMonthAndDate(Integer year,Integer month,Integer date);

}