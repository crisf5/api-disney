package com.disney.api.service.impl;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;
import com.disney.api.dto.CharacterFiltersDTO;
import com.disney.api.entity.CharacterEntity;
import com.disney.api.exception.ParamNotFound;
import com.disney.api.mapper.CharacterMapper;
import com.disney.api.repository.CharacterRepository;
import com.disney.api.repository.specification.CharacterSpecification;
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

    @Autowired
    private CharacterSpecification characterSpecification;

    @Override
    public List<CharacterBasicDTO> getCharactersBasic() {

        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2DTOBasicList(entities);
        return result;
    }

    @Override
    public CharacterDTO create(CharacterDTO dto) {

        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public CharacterDTO update(CharacterDTO dto, Long id) {

        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character ID is not Found.");
        }
        characterMapper.characterEntityRefreshValues(entity.get(), dto);
        CharacterEntity entitySaved = characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public void delete(Long id){
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character ID is not Found.");
        }
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterDTO findCharacterById(Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Character ID is not Found.");
        }
        CharacterDTO result = characterMapper.characterEntity2DTO(entity.get(), true);
        return result;
    }

    @Override
    public List<CharacterDTO> getCharactersByFilters(String name, Integer age, List<Long> movies) {

        CharacterFiltersDTO characterDTO = new CharacterFiltersDTO(name, age, movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(characterDTO));
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities, true);
        return result;
    }


}
