package com.disney.api.service.impl;

import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.dto.MovieDTO;
import com.disney.api.dto.MovieFiltersDTO;
import com.disney.api.entity.GenreEntity;
import com.disney.api.entity.MovieEntity;
import com.disney.api.exception.ParamNotFound;
import com.disney.api.mapper.MovieMapper;
import com.disney.api.repository.GenreRepository;
import com.disney.api.repository.MovieRepository;
import com.disney.api.repository.specification.MovieSpecification;
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

    @Autowired
    private GenreRepository genreRepository;

    @Autowired MovieSpecification movieSpecification;



    @Override
    public List<MovieBasicDTO> getMoviesBasic() {

        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieBasicDTO> result = movieMapper.movieEntityList2DTOBasicList(entities);
        return result;
    }

    @Override
    public MovieDTO findMovieById(Long id) {

        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("ID");
        }
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get(), true);
        return result;
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {

        Optional<GenreEntity> genre = genreRepository.findById(movieDTO.getGenreId());
        if (!genre.isPresent()){
            throw new ParamNotFound("Genre");
        }

        MovieEntity entity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO, Long id) {

        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("ID");
        }
        movieMapper.movieEntityRefreshValues(entity.get(), movieDTO);
        MovieEntity entitySaved = movieRepository.save(entity.get());
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("ID");
        }
        movieRepository.delete(entity.get());
    }

    @Override
    public List<MovieDTO> findMoviesByFilters(String title, Long genreId, String order) {
        if (order == null){order = "ASC";}
        MovieFiltersDTO movieFiltersDTO = new MovieFiltersDTO(title, genreId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(movieFiltersDTO));
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities, true);
        return result;
    }

    @Override
    public List<MovieBasicDTO> findMoviesBasicByFilters(String title, Long genreId, String order) {
        if (order == null){order = "ASC";}
        MovieFiltersDTO movieFiltersDTO = new MovieFiltersDTO(title, genreId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(movieFiltersDTO));
        List<MovieBasicDTO> result = movieMapper.movieEntityList2DTOBasicList(entities);
        return result;
    }


}
