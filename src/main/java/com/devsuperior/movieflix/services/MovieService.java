package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        return repository.searchById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Movie not founded")
                );
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findByGenre(Map<String, String> params) {
        Long genreId = params.containsKey("genreId") ? Long.parseLong(params.get("genreId")) : null;
        Pageable pageable = PageRequest.of(
                params.containsKey("page") ? Integer.parseInt(params.get("page")) : 0,
                params.containsKey("size") ? Integer.parseInt(params.get("size")) : 6
        );

        Page<MovieCardDTO> result = repository.searchAllOrderByTitle(pageable, genreId)
                .map(movie -> {
                   MovieCardDTO cardDTO = new MovieCardDTO();
                   cardDTO.setId( movie.getId() );
                   cardDTO.setTitle( movie.getTitle() );
                   cardDTO.setSubTitle( movie.getSubTitle() );
                   cardDTO.setYear( movie.getYear() );
                   cardDTO.setImgUrl(movie.getImgUrl() );
                   return cardDTO;
                });

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find movies");
        }

        return result;
    }
}
