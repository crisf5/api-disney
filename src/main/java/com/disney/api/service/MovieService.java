package com.disney.api.service;


import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieBasicDTO> getMoviesBasic();

    MovieDTO findMovieById(Long id);

    MovieDTO create(MovieDTO movieDTO);

    MovieDTO update(MovieDTO movieDTO, Long id);

    void delete(Long id);

    List<MovieDTO> findMoviesByFilters(String title, Long genreId, String order);

    List<MovieBasicDTO> findMoviesBasicByFilters(String title, Long genreId, String order);

}

