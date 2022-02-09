package com.disney.api.mapper;

import com.disney.api.dto.CharacterDTO;
import com.disney.api.entity.CharacterEntity;
import org.springframework.stereotype.Component;

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

}
