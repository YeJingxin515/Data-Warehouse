package com.dwh.backend.repository;

import com.dwh.backend.model.FilmActorMain;
import com.dwh.backend.model.FilmActorMainId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmActorMainRepository extends JpaRepository<FilmActorMain, FilmActorMainId> {
    List<FilmActorMain> findByActorName(String actorName);
}