package ru.practicum.shareit.item.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import ru.practicum.shareit.request.ItemRequest;

@Data
@Validated
@EqualsAndHashCode
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private ItemRequest request;

    private Long ownerId;
}
