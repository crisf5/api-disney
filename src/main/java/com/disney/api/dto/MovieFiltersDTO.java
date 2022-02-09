package com.disney.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieFiltersDTO {

    private String title;
    // TODO: 09/02/2022 Long or String? 
    private Long idGenre;

    private String order;

//    public MovieFiltersDTO(String title, Long idGenre, String order){
//        this.title = title;
//        this.idGenre = idGenre;
//        this.order = order;
//    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
