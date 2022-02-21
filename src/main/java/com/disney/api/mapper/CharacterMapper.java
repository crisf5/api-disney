package com.disney.api.mapper;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;
import com.disney.api.dto.MovieDTO;
import com.disney.api.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;


    public List<CharacterBasicDTO> characterEntityList2DTOBasicList(List<CharacterEntity> entities){

        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO characterBasicDTO;

        for (CharacterEntity entity : entities){
            characterBasicDTO = new CharacterBasicDTO();
            characterBasicDTO.setImage(entity.getImage());
            characterBasicDTO.setName(entity.getName());
            dtos.add(characterBasicDTO);
        }

        return dtos;
    }


    public CharacterEntity characterDTO2Entity(CharacterDTO dto){

        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        return entity;
    }


    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies){

        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());

        if(loadMovies){
            List<MovieDTO> movieDTOS = movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(movieDTOS);
        }

        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovies){

        List<CharacterDTO> dtos = new ArrayList<>();

        for (CharacterEntity entity : entities){
            dtos.add(characterEntity2DTO(entity, loadMovies));
        }

        return dtos;
    }

    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {

        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos) {

        List<CharacterEntity> entities = new ArrayList<>();

        for (CharacterDTO dto : dtos) {
            entities.add(characterDTO2Entity(dto));
        }

        return entities;
    }


}
