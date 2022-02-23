package com.disney.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    @NotBlank
    private String image;

    @Size(min = 2, max = 50)
    private String title;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato de la fecha debe ser yyyy/MM/dd")
    private String creatAt;

    @Min(1) @Max(5)
    private Integer rating;

    @NotNull @Min(1)
    private Long genreId;

    private List<CharacterDTO> characters = new ArrayList<>();
}
