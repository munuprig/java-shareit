package ru.practicum.shareit.item.service;

import ru.practicum.shareit.comment.dto.CommentDtoIn;
import ru.practicum.shareit.comment.dto.CommentDtoOut;
import ru.practicum.shareit.item.dto.ItemDtoIn;
import ru.practicum.shareit.item.dto.ItemDtoOut;

import java.util.List;

public interface ItemService {
    ItemDtoOut getById(Long itemId, Long userId);

    List<ItemDtoOut> getByOwner(Long userId);

    List<ItemDtoOut> getBySearch(String text);

    ItemDtoOut create(ItemDtoIn itemDtoIn, Long userId);

    ItemDtoOut update(Long itemId, ItemDtoIn itemDtoIn, Long userId);

    CommentDtoOut createComment(Long itemId, CommentDtoIn commentDtoIn, Long userId);
}
