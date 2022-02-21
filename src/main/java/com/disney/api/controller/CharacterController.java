package com.disney.api.controller;

import com.disney.api.dto.CharacterBasicDTO;
import com.disney.api.dto.CharacterDTO;
import com.disney.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getCharacterBasicList(){

        List<CharacterBasicDTO> characterBasicDTOS = characterService.getCharactersBasic();
        return ResponseEntity.ok(characterBasicDTOS);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@Valid @RequestBody CharacterDTO characterDTO){

        CharacterDTO characterSaved = characterService.create(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){

        CharacterDTO characterUpdated = characterService.update(characterDTO, id);
        return ResponseEntity.ok().body(characterUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id){

        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id){

        CharacterDTO characterDetails = characterService.findCharacterById(id);
        return ResponseEntity.ok().body(characterDetails);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<CharacterDTO>> getCharactersByFilters(
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) List<Long> movies
    ){
        List<CharacterDTO> characterFilters = characterService.findCharactersByFilters(name, age, movies);
        return ResponseEntity.ok(characterFilters);
    }


}
