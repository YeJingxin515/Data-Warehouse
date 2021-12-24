package com.dwh.backend.repository;

import com.dwh.backend.model.FilmDirector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmDirectorRepository extends JpaRepository<FilmDirector, Integer> {
    List<FilmDirector> findByDirectorName(String directorName);
}