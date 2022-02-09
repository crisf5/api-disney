package com.disney.api.dto;

import com.disney.api.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private String image;

    private String name;

    private Integer age;

    private Integer weight;

    private String history;

    private List<MovieEntity> movies = new ArrayList<>();

}
