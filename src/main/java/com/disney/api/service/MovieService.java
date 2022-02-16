package com.disney.api.service;


import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieBasicDTO> getAllMoviesBasic();

    MovieDTO findMovieById(Long id);

    MovieDTO create(MovieDTO movieDTO);

}
