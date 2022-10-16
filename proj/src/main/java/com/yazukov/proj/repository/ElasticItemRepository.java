package com.yazukov.proj.repository;

import com.yazukov.proj.domain.ElasticItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticItemRepository extends CrudRepository<ElasticItem, Long> {

    List<ElasticItem> findByName(String itemName);

    List<ElasticItem> findByTags(String tag);
}
