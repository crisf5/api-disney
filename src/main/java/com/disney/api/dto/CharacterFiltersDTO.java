package com.disney.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CharacterFiltersDTO {

    private String name;
    private Integer age;
    private List<Long> movies;

    // TODO: 14/02/2022 Es Long en vez de MovieDTO!
}
