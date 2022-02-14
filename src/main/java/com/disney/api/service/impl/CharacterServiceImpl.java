package com.disney.api.service.impl;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;
import com.disney.api.entity.CharacterEntity;
import com.disney.api.mapper.CharacterMapper;
import com.disney.api.repository.CharacterRepository;
import com.disney.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public List<CharacterBasicDTO> getAllCharactersBasic() {

        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2DTOBasic(entities);
        return result;

    }

    @Override
    public CharacterDTO save(CharacterDTO dto) {

        CharacterEntity entity = characterMapper.CharacterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.CharacterEntity2DTO(entitySaved, true);

        return result;
    }


}
