package com.yazukov.proj.service;

import com.yazukov.proj.domain.Collection;
import com.yazukov.proj.domain.Item;
import com.yazukov.proj.domain.ItemTag;
import com.yazukov.proj.dto.ItemDto;
import com.yazukov.proj.exception.CollectionNotFoundException;
import com.yazukov.proj.exception.ItemNotFoundException;
import com.yazukov.proj.mapper.ItemMapper;
import com.yazukov.proj.mapper.ItemTagMapper;
import com.yazukov.proj.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CollectionRepository collectionRepository;
    private final ItemTagRepository itemTagRepository;
    private final ItemTagMapper itemTagMapper;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ElasticSearchService elasticSearchService;

    public List<ItemDto> getItemsByCollectionId(Long collectionId){
        return itemRepository.findByCollectionId(collectionId).stream().map(itemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }

    public ItemDto getItemById(Long itemId) throws ItemNotFoundException {
        return itemMapper.itemToItemDto(itemRepository.findById(itemId).orElseThrow(()->
                new ItemNotFoundException("Item not found")));
    }

    @Transactional
    public ItemDto createNewItem(Long collectionId, Long userId, ItemDto itemDto) throws CollectionNotFoundException {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(()->
                new CollectionNotFoundException("Collection for this item couldn't found"));
        Set<ItemTag> tags = itemDto.getTags().stream().map(itemTagMapper::itemTagDtoToItemTag)
                .map(tag -> {
                    ItemTag res;
                    Optional<ItemTag> byName = itemTagRepository.findByName(tag.getName());
                    if(byName.isEmpty()) res = itemTagRepository.save(tag);
                    else res = byName.get();
                    return res;
                })
                .collect(Collectors.toSet());
        Item item = itemMapper.itemDtoToItem(itemDto);
        item.setTags(tags);
        item.setCollection(collection);
        Item savedItem = itemRepository.save(item);
        elasticSearchService.addItemAtRepo(savedItem);
        return itemMapper.itemToItemDto(savedItem);
    }

    @Transactional
    public ItemDto updateItem(ItemDto itemDto) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemDto.getId()).orElseThrow(()->
                new ItemNotFoundException("Item not found"));
        Set<ItemTag> tags = itemDto.getTags().stream().map(itemTagMapper::itemTagDtoToItemTag)
                .map(tag -> {
                    ItemTag res;
                    Optional<ItemTag> byName = itemTagRepository.findByName(tag.getName());
                    if(byName.isEmpty()) res = itemTagRepository.save(tag);
                    else res = byName.get();
                    return res;
                })
                .collect(Collectors.toSet());
        item.setTags(tags);
        item.setFields(itemDto.getFields());
        item.setName(itemDto.getName());
        Item updatedItem = itemRepository.save(item);
        elasticSearchService.addItemAtRepo(updatedItem);
        return itemMapper.itemToItemDto(updatedItem);
    }

    @Transactional
    public void deleteItem(Long itemId) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(()->
                new ItemNotFoundException("Item not found"));
        item.getComments().stream().peek(cm -> cm.getLikies().forEach(likeRepository::delete))
                .forEach(commentRepository::delete);
        elasticSearchService.deleteItemFromRepo(item);
        itemRepository.delete(item);
    }
}
