package com.disney.api.service.impl;

import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.dto.MovieDTO;
import com.disney.api.entity.MovieEntity;
import com.disney.api.exception.ParamNotFound;
import com.disney.api.mapper.MovieMapper;
import com.disney.api.repository.MovieRepository;
import com.disney.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public MovieDTO findMovieById(Long id) {

        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Movie ID is not valid");
        }
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get(), true);
        return result;
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {

        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity entitySaved = movieRepository.save(movieEntity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }
}
