package com.disney.api.dto;

import com.disney.api.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterFiltersDTO {

    private String name;

    private Integer age;

    private List<MovieEntity> movies = new ArrayList<>();

}
