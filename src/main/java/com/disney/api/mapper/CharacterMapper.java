package com.disney.api.mapper;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {



    public List<CharacterBasicDTO> characterEntityList2DTOBasic(List<CharacterEntity> entities){

        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;

        for (CharacterEntity entity : entities){
            basicDTO = new CharacterBasicDTO();
            basicDTO.setImage(entity.getImage());
            basicDTO.setName(entity.getName());
            dtos.add(basicDTO);

        }

        return dtos;

    }

    //Probando!


}
