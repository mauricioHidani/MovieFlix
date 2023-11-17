package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.ReviewWithUserNameDTO;
import com.devsuperior.movieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT new com.devsuperior.movieflix.dto.ReviewWithUserNameDTO(r.id, r.text, u.name)
        FROM Review r
        INNER JOIN User u ON u.id = r.user.id
        WHERE r.movie.id = :movieId
    """)
    List<ReviewWithUserNameDTO> searchByMovieId(Long movieId);
}
