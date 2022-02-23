package com.disney.api.service.impl;

import com.disney.api.dto.GenreDTO;
import com.disney.api.entity.GenreEntity;
import com.disney.api.mapper.GenreMapper;
import com.disney.api.repository.GenreRepository;
import com.disney.api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public GenreDTO create(GenreDTO genreDTO) {

        GenreEntity entity = genreMapper.genreDTO2Entity(genreDTO);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);

        return result;
    }
}
