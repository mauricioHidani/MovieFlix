package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO review) {
        Review save = new Review();
        save.setText(review.getText());

        save.setMovie(new Movie());
        save.getMovie().setId(review.getMovieId());
        save.setUser(authService.authenticated());

        Long idSaved = repository.save(save).getId();
        review.setId( idSaved );
        review.setUserId(save.getUser().getId());
        review.setUserName(save.getUser().getName());
        review.setUserEmail(save.getUser().getEmail());

        return review;
    }
}
