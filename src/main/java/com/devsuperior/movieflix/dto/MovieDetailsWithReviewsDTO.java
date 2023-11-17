package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsWithReviewsDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
    private String synopsis;
    private String genreName;
    private List<ReviewWithUserNameDTO> reviews = new ArrayList<>();

    public MovieDetailsWithReviewsDTO() {
    }

    public MovieDetailsWithReviewsDTO(Long id, String title, String subTitle, Integer year, String imgUrl,
                                      String synopsis, String genreName) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
        this.genreName = genreName;
    }

    public MovieDetailsWithReviewsDTO(MovieDetailsDTO detailsDTO) {
        this.id = detailsDTO.getId();
        this.title = detailsDTO.getTitle();
        this.subTitle = detailsDTO.getSubTitle();
        this.year = detailsDTO.getYear();
        this.imgUrl = detailsDTO.getImgUrl();
        this.synopsis = detailsDTO.getSynopsis();
        this.genreName = detailsDTO.getGenre().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<ReviewWithUserNameDTO> getReviews() {
        return reviews;
    }

    public void addReview(ReviewWithUserNameDTO review) {
        this.reviews.add(review);
    }
}
