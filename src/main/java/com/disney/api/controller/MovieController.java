package com.disney.api.controller;

import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getMovieBasicList(){

        List<MovieBasicDTO> movieBasicDTOS = movieService.getAllMoviesBasic();
        return ResponseEntity.ok(movieBasicDTOS);
    }


}
