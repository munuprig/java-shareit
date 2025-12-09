package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto getItemById(long itemId);

    List<ItemDto> getItemsByOwner(long userId);

    List<ItemDto> getItemBySearch(String text);

    ItemDto create(ItemDto itemDto, long userId);

    ItemDto update(long itemId, ItemDto itemDto, long userId);
}
