package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    public List<GenreDTO> findAll() {
        List<GenreDTO> result = repository.findAll()
                .stream()
                .map(genre -> {
                    GenreDTO dto = new GenreDTO();
                    dto.setId( genre.getId() );
                    dto.setName( genre.getName() );
                    return dto;
                })
                .toList();

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find movie genres");
        }

        return result;
    }
}