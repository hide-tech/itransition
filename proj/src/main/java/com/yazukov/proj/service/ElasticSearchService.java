package com.yazukov.proj.service;

import com.yazukov.proj.domain.ElasticItem;
import com.yazukov.proj.domain.Item;
import com.yazukov.proj.dto.ItemDto;
import com.yazukov.proj.mapper.ItemMapper;
import com.yazukov.proj.repository.ElasticItemRepository;
import com.yazukov.proj.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElasticSearchService {

    private final ElasticItemRepository elasticItemRepository;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Transactional
    public List<ItemDto> searchByName(String itemName){
        List<ElasticItem> searchItems = elasticItemRepository.findByName(itemName);
        return searchItems.stream().map(el -> {
            return itemRepository.findById(el.getId()).get();
        }).map(itemMapper::itemToItemDto).collect(Collectors.toList());
    }

    @Transactional
    public List<ItemDto> searchByTags(List<String> tags){
        List<ElasticItem> items = elasticItemRepository.findByTags(tags.get(0));
        return items.stream().filter(eltag -> {
            return tags.stream().allMatch(tg -> eltag.getTags().contains(tg));
        }).map(el -> {
            return itemRepository.findById(el.getId()).get();
        }).map(itemMapper::itemToItemDto).collect(Collectors.toList());
    }

    public void addItemAtRepo(Item item){
        ElasticItem elasticItem = new ElasticItem();
        elasticItem.setId(item.getId());
        elasticItem.setName(item.getName());
        elasticItem.setFields(item.getFields());
        String elTags = "";
        item.getTags().forEach(tag -> elTags.concat(tag.getName() +" "));
        elasticItem.setTags(elTags);
        elasticItemRepository.save(elasticItem);
    }

    public void deleteItemFromRepo(Item item){
        ElasticItem elasticItem = new ElasticItem();
        elasticItem.setId(item.getId());
        elasticItem.setName(item.getName());
        elasticItem.setFields(item.getFields());
        String elTags = "";
        item.getTags().forEach(tag -> elTags.concat(tag.getName() + " "));
        elasticItem.setTags(elTags);
        elasticItemRepository.delete(elasticItem);
    }


}
