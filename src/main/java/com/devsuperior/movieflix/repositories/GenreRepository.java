package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("""
           SELECT new com.devsuperior.movieflix.dto.GenreDTO(g.id, g.name)
           FROM Genre g
           """)
    List<GenreDTO> searchAll();
}
