package com.dwh.backend.repository;

import com.dwh.backend.model.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmGenreRepository extends JpaRepository<FilmGenre, Integer> {

    List<FilmGenre> findByGenreName(String name);
}