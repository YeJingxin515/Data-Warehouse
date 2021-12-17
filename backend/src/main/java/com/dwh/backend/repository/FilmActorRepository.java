package com.dwh.backend.repository;

import com.dwh.backend.model.FilmActor;
import com.dwh.backend.model.FilmActorId;
import com.dwh.backend.model.FilmActorMain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
    List<FilmActor> findByActorName(String actorName);
}