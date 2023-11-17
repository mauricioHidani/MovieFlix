package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.*;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        return repository.searchById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Movie not founded")
                );
    }

    @Transactional(readOnly = true)
    public MovieDetailsWithReviewsDTO findByIdWithReviews(Long id) {
        List<ReviewWithUserNameDTO> reviewsFounded = reviewRepository.searchByMovieId(id);
        MovieDetailsWithReviewsDTO founded = new MovieDetailsWithReviewsDTO(findById(id));

        reviewsFounded.forEach(review -> {
            ReviewWithUserNameDTO reviewWithUserName = new ReviewWithUserNameDTO();
            reviewWithUserName.setId(review.getId());
            reviewWithUserName.setText(review.getText());
            reviewWithUserName.setUserName(review.getUserName());

            founded.addReview(reviewWithUserName);
        });

        return founded;
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findByGenre(Map<String, String> params) {
        Long genreId = params.containsKey("genreId") ? Long.parseLong(params.get("genreId")) : null;
        Pageable pageable = PageRequest.of(
                params.containsKey("page") ? Integer.parseInt(params.get("page")) : 0,
                params.containsKey("size") ? Integer.parseInt(params.get("size")) : 6
        );

        Page<MovieCardDTO> result = repository.searchAllOrderByTitle(pageable, genreId);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find movies");
        }

        return result;
    }
}
