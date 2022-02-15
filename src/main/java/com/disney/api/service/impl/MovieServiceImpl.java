package com.disney.api.service.impl;

import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.entity.MovieEntity;
import com.disney.api.mapper.MovieMapper;
import com.disney.api.repository.MovieRepository;
import com.disney.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<MovieBasicDTO> getAllMoviesBasic() {

        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieBasicDTO> result = movieMapper.movieEntityList2DTOBasicList(entities);
        return result;
    }
}
