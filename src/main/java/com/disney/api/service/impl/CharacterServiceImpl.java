package com.disney.api.service.impl;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;
import com.disney.api.entity.CharacterEntity;
import com.disney.api.exception.ParamNotFound;
import com.disney.api.mapper.CharacterMapper;
import com.disney.api.repository.CharacterRepository;
import com.disney.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public CharacterDTO create(CharacterDTO dto) {

        CharacterEntity entity = characterMapper.CharacterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.CharacterEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public CharacterDTO update(CharacterDTO dto, Long id) {

        //CharacterEntity entity = characterRepository.getById(id);
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character ID is not Valid");
        }
        characterMapper.characterEntityRefreshValues(entity.get(), dto);
        CharacterEntity entitySaved = characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.CharacterEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public void delete(Long id){
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character ID is not Valid");
        }
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterDTO findCharacterById(Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character ID is not Valid");
        }
        CharacterDTO result = characterMapper.CharacterEntity2DTO(entity.get(), true);
        return result;
    }


}
