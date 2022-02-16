package com.disney.api.repository.specification;

import com.disney.api.dto.MovieFiltersDTO;
import com.disney.api.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDto.getTitle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDto.getTitle().toLowerCase() + "%")
                );
            }

            if (filtersDto.getIdGenre() != null){
                predicates.add(criteriaBuilder.equal(root.get("genreId"),filtersDto.getIdGenre()));
            }


            String orderByAsc = "creatAt";
            query.orderBy(
                    filtersDto.isAsc() ?
                            criteriaBuilder.asc(root.get(orderByAsc)):
                            criteriaBuilder.desc(root.get(orderByAsc))
            );

            String orderByDesc = "creatAt";
            query.orderBy(
                    filtersDto.isDesc() ?
                            criteriaBuilder.desc(root.get(orderByDesc)):
                            criteriaBuilder.asc(root.get(orderByDesc))
            );


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
