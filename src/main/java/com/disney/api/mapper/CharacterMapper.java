package com.disney.api.mapper;

import com.disney.api.dto.CharacterDTO;
import com.disney.api.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    //Basic in
    public CharacterEntity characterDTO2Entity(CharacterDTO dto){

        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());

        return characterEntity;

    }

    //Basic out
    public CharacterDTO characterEntity2DTO(CharacterEntity entity){

        CharacterDTO characterDTO = new CharacterDTO();

        characterDTO.setId(entity.getId());
        characterDTO.setImage(entity.getImage());
        characterDTO.setName(entity.getName());

        return characterDTO;

    }

    //All in
    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos){

        List<CharacterEntity> entities = new ArrayList<>();

        for (CharacterDTO dto : dtos){
            entities.add(characterDTO2Entity(dto));
        }

        return entities;

    }

    //All out
    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities){

        List<CharacterDTO> dtos = new ArrayList<>();

        for (CharacterEntity entity : entities){
            dtos.add(characterEntity2DTO(entity));
        }

        return dtos;

    }

}
