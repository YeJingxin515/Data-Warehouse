package com.dwh.backend.repository;

import com.dwh.backend.model.FilmFormat;
import com.dwh.backend.model.FilmFormatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmFormatRepository extends JpaRepository<FilmFormat, FilmFormatId> {
    List<FilmFormat> findByFilmTitle(String filmTitle);
    List<FilmFormat> findByFormatName(String formatName);
}