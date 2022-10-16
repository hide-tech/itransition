package com.yazukov.proj.mapper;

import com.yazukov.proj.domain.ItemTag;
import com.yazukov.proj.dto.ItemTagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemTagMapper {

    ItemTagDto itemTagToItemTagDto(ItemTag itemTag);

    ItemTag itemTagDtoToItemTag(ItemTagDto itemTagDto);
}
