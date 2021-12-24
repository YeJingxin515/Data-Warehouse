package com.dwh.backend.repository;

import com.dwh.backend.model.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {
    List<FilmActor> findByActorName(String actorName);
}