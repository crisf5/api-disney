package com.disney.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private Long id;

    @NotBlank
    private String image;

    @Size(min = 2, max = 24)
    private String name;

    @Min(0)
    private Integer age;

    @Min(1)
    private Integer weight;

    @NotBlank
    private String history;

    private List<MovieDTO> movies = new ArrayList<>();
}
