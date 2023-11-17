package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
            SELECT m
            FROM Movie m
            INNER JOIN Genre g
            WHERE m.id = :id
            """)
    Optional<Movie> searchById(Long id);

    @Query("""
        SELECT m
        FROM Movie m
        WHERE (:genreId IS NULL OR m.genre.id = :genreId)
        ORDER BY m.title ASC
        """)
    Page<Movie> searchAllOrderByTitle(Pageable pageable, @Param("genreId") Long genreId);
}
