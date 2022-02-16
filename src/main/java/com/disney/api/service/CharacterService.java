package com.disney.api.service;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    List<CharacterBasicDTO> getCharactersBasic();

    CharacterDTO create(CharacterDTO dto);

    CharacterDTO update(CharacterDTO dto, Long id);

    void delete(Long id);

    CharacterDTO findCharacterById(Long id);

    List<CharacterDTO> getCharactersByFilters(String name, Integer age, List<Long> movies);

}
