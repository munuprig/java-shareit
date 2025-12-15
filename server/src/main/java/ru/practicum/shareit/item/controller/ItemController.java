package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.comment.dto.CommentDtoIn;
import ru.practicum.shareit.comment.dto.CommentDtoOut;
import ru.practicum.shareit.item.dto.ItemDtoIn;
import ru.practicum.shareit.item.dto.ItemDtoOut;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDtoOut saveNewItem(@RequestBody ItemDtoIn itemDtoIn,
                                  @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.saveNewItem(itemDtoIn, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDtoOut updateItem(@PathVariable Long itemId,
                                 @RequestBody ItemDtoIn itemDtoIn,
                                 @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.updateItem(itemId, itemDtoIn, userId);
    }

    @GetMapping("/{itemId}")
    public ItemDtoOut getItemById(@PathVariable Long itemId,
                                  @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItemById(itemId, userId);
    }

    @GetMapping
    public List<ItemDtoOut> getItemsByOwner(@RequestParam(defaultValue = "1") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItemsByOwner(from, size, userId);
    }

    @GetMapping("/search")
    public List<ItemDtoOut> getItemBySearch(@RequestParam(defaultValue = "1") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam String text) {
        return itemService.getItemBySearch(from, size, text);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDtoOut saveNewComment(@PathVariable Long itemId,
                                        @RequestBody CommentDtoIn commentDtoIn,
                                        @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.saveNewComment(itemId, commentDtoIn, userId);
    }
}
