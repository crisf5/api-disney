package com.disney.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private String creatAt;

    @Min(1) @Max(5)
    private Integer rating;

    private Long genreId;

    private List<CharacterDTO> characters = new ArrayList<>();
}
