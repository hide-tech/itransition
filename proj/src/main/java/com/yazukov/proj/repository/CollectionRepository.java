package com.yazukov.proj.repository;

import com.yazukov.proj.domain.Collection;
import com.yazukov.proj.dto.CollectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("select distinct new com.yazukov.proj.dto.CollectionDto(c.id, c.name, c.description, c.theme, c.photoUri) " +
            "from Collection c " +
            "where c.user.id = ?1")
    List<CollectionDto> findCollectionsByUserId(Long userId);
}
