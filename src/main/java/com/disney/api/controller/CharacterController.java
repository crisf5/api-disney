package com.disney.api.controller;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getCharacterBasicList(CharacterBasicDTO character){

        List<CharacterBasicDTO> characterBasicDTOList = characterService.getAllCharactersBasic();
        return ResponseEntity.ok(characterBasicDTOList);
    }

}
