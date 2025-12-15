package ru.practicum.shareit.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.service.ItemRequestService;
import ru.practicum.shareit.request.dto.ItemRequestDtoIn;
import ru.practicum.shareit.request.dto.ItemRequestDtoOut;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests")
public class ItemRequestController {
    private final ItemRequestService requestService;

    @PostMapping
    public ItemRequestDtoOut saveNewRequest(@RequestBody ItemRequestDtoIn requestDtoIn,
                                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.saveNewRequest(requestDtoIn, userId);
    }

    @GetMapping
    public List<ItemRequestDtoOut> getRequestsByRequestor(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.getRequestsByRequestor(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDtoOut> getAllRequests(@RequestParam(defaultValue = "1") Integer from,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 @RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.getAllRequests(from, size, userId);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDtoOut getRequestById(@PathVariable Long requestId,
                                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.getRequestById(requestId, userId);
    }
}
