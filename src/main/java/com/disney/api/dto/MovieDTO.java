package com.disney.api.dto;

import com.disney.api.entity.CharacterEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDate creatAt;

    private Integer rating;

    private Set<CharacterEntity> characters = new HashSet<>();

}
