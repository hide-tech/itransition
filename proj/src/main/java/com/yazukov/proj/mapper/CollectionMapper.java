package com.yazukov.proj.mapper;

import com.yazukov.proj.domain.Collection;
import com.yazukov.proj.dto.CollectionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDto collectionToCollectionDto(Collection collection);

    Collection collectionDtoToCollection(CollectionDto collectionDto);
}
