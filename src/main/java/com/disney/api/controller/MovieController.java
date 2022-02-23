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

        List<MovieBasicDTO> movieBasicDTOS = movieService.getMoviesBasic();
        return ResponseEntity.ok(movieBasicDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id){

        MovieDTO movieDTO = movieService.findMovieById(id);
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) {

        MovieDTO movieSaved = movieService.create(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO dto,@PathVariable Long id){

        MovieDTO movieDTO = movieService.update(dto, id);
        return ResponseEntity.ok(movieDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){

        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/filters")
    public ResponseEntity<List<MovieDTO>> getMoviesByFilters(
            @RequestParam (required = false) String title,
            @RequestParam (required = false) Long genreId,
            @RequestParam (required = false) String order
    ){
        List<MovieDTO> movieDTOS = movieService.findMoviesByFilters(title, genreId, order);
        return ResponseEntity.ok(movieDTOS);
    }

    
}
