package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
        SELECT new com.devsuperior.movieflix.dto.MovieDetailsDTO(
            m.id, m.title, m.subTitle, m.year, m.imgUrl, m.synopsis, m.genre.id, m.genre.name
        )
        FROM Movie m
        INNER JOIN Genre g ON g.id = m.genre.id
        WHERE m.id = :id
    """)
    Optional<MovieDetailsDTO> searchById(Long id);

    @Query("""
        SELECT new com.devsuperior.movieflix.dto.MovieCardDTO(m)
        FROM Movie m
        WHERE (:genreId IS NULL OR m.genre.id = :genreId)
        ORDER BY m.title ASC
    """)
    Page<MovieCardDTO> searchAllOrderByTitle(Pageable pageable, @Param("genreId") Long genreId);
}
