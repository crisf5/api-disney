package com.disney.api.controller;

import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.dto.MovieDTO;
import com.disney.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id){

        MovieDTO movieDTO = movieService.findMovieById(id);
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO dto) {

        MovieDTO movieDTO = movieService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);
    }




}
