package com.yazukov.proj.repository;

import com.yazukov.proj.domain.Item;
import com.yazukov.proj.dto.ItemDto;
import com.yazukov.proj.dto.ItemDtoComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i " +
            "from Item i " +
            "where i.collection.id = ?1")
    List<Item> findByCollectionId(Long collectionId);
}
