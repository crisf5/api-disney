package com.disney.api.repository.specification;

import com.disney.api.dto.CharacterFiltersDTO;
import com.disney.api.entity.CharacterEntity;
import com.disney.api.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {

        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {

                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + filtersDTO.getName().toLowerCase() + "%"
                ));
            }

            if (filtersDTO.getAge() != null) {

                predicates.add(criteriaBuilder.equal(root.get("age"), filtersDTO.getAge()));

            }

            if (filtersDTO.getWeight() != null){
                predicates.add((criteriaBuilder.equal(root.get("weight"),filtersDTO.getWeight())));
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getIdMovies())) {

                Join<CharacterEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getIdMovies()));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
