package com.dwh.backend.repository;

import com.dwh.backend.model.FilmDirector;
import com.dwh.backend.model.FilmDirectorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmDirectorRepository extends JpaRepository<FilmDirector, FilmDirectorId> {
    List<FilmDirector> findByDirectorName(String directorName);
}