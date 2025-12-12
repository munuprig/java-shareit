package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comment.dto.CommentDtoIn;
import ru.practicum.shareit.comment.dto.CommentDtoOut;
import ru.practicum.shareit.item.dto.ItemDtoOut;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.dto.ItemDtoIn;
import ru.practicum.shareit.util.Create;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public ItemDtoOut getById(@PathVariable Long itemId, @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("GET / items {} / user {}", itemId, userId);
        return itemService.getById(itemId, userId);
    }

    @GetMapping
    public List<ItemDtoOut> getByOwner(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("GET / items / user {}", userId);
        return itemService.getByOwner(userId);
    }

    @GetMapping("/search")
    public List<ItemDtoOut> getBySearch(@RequestParam String text) {
        log.info("GET / search / {}", text);
        return itemService.getBySearch(text);
    }

    @PostMapping
    public ItemDtoOut create(@Validated(Create.class) @RequestBody ItemDtoIn itemDto,
                             @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("POST / items / {}", itemDto.getName());
        return itemService.create(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDtoOut update(@PathVariable Long itemId, @RequestBody ItemDtoIn itemDto,
                             @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("PATCH / items / {}", itemId);
        return itemService.update(itemId, itemDto, userId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDtoOut addComment(@PathVariable Long itemId,
                                    @Validated(Create.class) @RequestBody CommentDtoIn commentDtoIn,
                                    @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("POST / comment / item {}", itemId);
        return itemService.createComment(itemId, commentDtoIn, userId);
    }
}
