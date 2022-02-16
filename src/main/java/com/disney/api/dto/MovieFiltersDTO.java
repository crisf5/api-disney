package com.disney.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieFiltersDTO {

    private String title;
    private Long IdGenre;
    private String order;

    public boolean isAsc(){return order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDesc(){return order.compareToIgnoreCase("DESC") == 0;}

}
