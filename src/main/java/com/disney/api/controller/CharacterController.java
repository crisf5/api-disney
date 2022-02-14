package com.disney.api.controller;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;
import com.disney.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getCharacterBasicList(){

        List<CharacterBasicDTO> characterBasicDTOList = characterService.getAllCharactersBasic();
        return ResponseEntity.ok(characterBasicDTOList);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterDTO characterDTO){

        CharacterDTO characterSaved = characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }


}
