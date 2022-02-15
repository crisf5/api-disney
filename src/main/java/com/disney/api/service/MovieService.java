package com.disney.api.service;


import com.disney.api.dto.MovieBasicDTO;

import java.util.List;

public interface MovieService {

    List<MovieBasicDTO> getAllMoviesBasic();

}
