package com.dwh.backend.repository;

import com.dwh.backend.model.FilmTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmTimeRepository extends JpaRepository<FilmTime, Integer> {
    List<FilmTime> findByYear(Integer year);
    List<FilmTime> findByYearAndMonth(Integer year,String month);
    List<FilmTime> findByYearAndQuarter(Integer year,Integer quarter);
}