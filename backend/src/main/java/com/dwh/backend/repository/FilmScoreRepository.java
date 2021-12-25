package com.dwh.backend.repository;

import com.dwh.backend.model.FilmScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

public interface FilmScoreRepository extends JpaRepository<FilmScore, Integer> {
}