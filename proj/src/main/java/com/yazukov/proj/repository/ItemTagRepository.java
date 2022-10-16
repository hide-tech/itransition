package com.yazukov.proj.repository;

import com.yazukov.proj.domain.ItemTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemTagRepository extends JpaRepository<ItemTag, Long> {

    Optional<ItemTag> findByName(String name);
}
