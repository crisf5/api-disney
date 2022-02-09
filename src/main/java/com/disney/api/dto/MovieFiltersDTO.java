package com.disney.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieFiltersDTO {

    private String title;

    private Long genre_id;

    // TODO: 09/02/2022 order ASC/DESC
}
