package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsWithReviewsDTO extends MovieDetailsDTO {

    private List<ReviewWithUserNameDTO> reviews = new ArrayList<>();

    public MovieDetailsWithReviewsDTO() {
    }

    public MovieDetailsWithReviewsDTO(Long id, String title, String subTitle, Integer year, String imgUrl,
                                      String synopsis, Long genreId, String genreName) {
        super(id, title, subTitle, year, imgUrl, synopsis, genreId, genreName);
    }

    public MovieDetailsWithReviewsDTO(MovieDetailsDTO detailsDTO) {
        super(
            detailsDTO.getId(),
            detailsDTO.getTitle(),
            detailsDTO.getSubTitle(),
            detailsDTO.getYear(),
            detailsDTO.getImgUrl(),
            detailsDTO.getSynopsis(),
            detailsDTO.getGenre().getId(),
            detailsDTO.getGenre().getName()
        );
    }

    public List<ReviewWithUserNameDTO> getReviews() {
        return reviews;
    }

    public void addReview(ReviewWithUserNameDTO review) {
        this.reviews.add(review);
    }
}
