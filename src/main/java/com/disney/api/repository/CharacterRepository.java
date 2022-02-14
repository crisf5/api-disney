package com.disney.api.repository;

import com.disney.api.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {


}
