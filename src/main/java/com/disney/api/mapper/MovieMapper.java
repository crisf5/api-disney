package com.disney.api.mapper;

import com.disney.api.dto.CharacterDTO;
import com.disney.api.dto.MovieBasicDTO;
import com.disney.api.dto.MovieDTO;
import com.disney.api.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters){

        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity entity : entities ){
            dtos.add(movieEntity2DTO(entity, loadCharacters));
        }

        return dtos;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters){

        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreatAt(entity.getCreatAt());
        dto.setRating(entity.getRating());
        dto.setGenreId(entity.getGenreId());

        if(loadCharacters){
            List<CharacterDTO> characterDTOS = characterMapper.characterEntityList2DTOList(entity.getCharacters(), false);
            dto.setCharacters(characterDTOS);
        }

        return dto;
    }

    public List<MovieBasicDTO> movieEntityList2DTOBasicList(List<MovieEntity> entities){

        List<MovieBasicDTO> dtos = new ArrayList<>();
        MovieBasicDTO movieBasicDTO;

        for(MovieEntity entity : entities){
            movieBasicDTO = new MovieBasicDTO();
            movieBasicDTO.setImage(entity.getImage());
            movieBasicDTO.setTitle(entity.getTitle());
            movieBasicDTO.setCreatAt(entity.getCreatAt());
            dtos.add(movieBasicDTO);
        }

        return dtos;
    }

}
