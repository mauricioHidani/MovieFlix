package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    public ResponseEntity<MovieDetailsDTO> findByGenre(@PathVariable Long id) {
        MovieDetailsDTO result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    public ResponseEntity<Page<MovieCardDTO>> findByGenre(@RequestParam Map<String, String> params) {
        Page<MovieCardDTO> result = service.findByGenre(params);
        return ResponseEntity.ok(result);
    }
}
