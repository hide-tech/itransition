package com.yazukov.proj.mapper;

import com.yazukov.proj.domain.Item;
import com.yazukov.proj.dto.ItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item itemDtoToItem(ItemDto itemDto);

    ItemDto itemToItemDto(Item item);
}
