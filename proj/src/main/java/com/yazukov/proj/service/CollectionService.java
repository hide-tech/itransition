package com.yazukov.proj.service;

import com.yazukov.proj.domain.Collection;
import com.yazukov.proj.domain.User;
import com.yazukov.proj.dto.CollectionDto;
import com.yazukov.proj.exception.CollectionNotFoundException;
import com.yazukov.proj.exception.UserNotFoundException;
import com.yazukov.proj.mapper.CollectionMapper;
import com.yazukov.proj.repository.CollectionRepository;
import com.yazukov.proj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;
    private final UserRepository userRepository;

    public List<CollectionDto> getCollectionByUserId(Long userId){
        return collectionRepository.findCollectionsByUserId(userId);
    }

    @Transactional
    public CollectionDto createNewCollection(Long userId, CollectionDto collectionDto)
            throws UserNotFoundException{
        Collection collection = collectionMapper.collectionDtoToCollection(collectionDto);
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("Collection owner not found"));
        collection.setUser(user);
        Collection savedCollection = collectionRepository.save(collection);
        return collectionMapper.collectionToCollectionDto(savedCollection);
    }

    @Transactional
    public CollectionDto updateCollection(CollectionDto collectionDto)
            throws CollectionNotFoundException {
        Collection collection = collectionRepository.findById(collectionDto.getId()).orElseThrow(()->
                new CollectionNotFoundException("Collection doesn't exist"));
        collection.setDescription(collectionDto.getDescription());
        collection.setName(collectionDto.getName());
        collection.setTheme(collectionDto.getTheme());
        collection.setPhotoUri(collectionDto.getPhotoUri());
        Collection updatedCollection = collectionRepository.save(collection);
        return collectionMapper.collectionToCollectionDto(updatedCollection);
    }

    public void deleteCollection(Long collectionId) throws CollectionNotFoundException {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(()->
                new CollectionNotFoundException("Collection doesn't exist"));
            collectionRepository.delete(collection);
    }

    private User addCollectionToUserCollections(User user, Collection collection){
        List<Collection> collections = user.getCollections();
        collections.add(collection);
        user.setCollections(collections);
        return user;
    }
}
