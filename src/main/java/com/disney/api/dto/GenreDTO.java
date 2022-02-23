package com.disney.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GenreDTO {

    private Long id;

    @Size(min = 2, max = 24)
    private String name;

    @NotBlank
    private String image;
}
