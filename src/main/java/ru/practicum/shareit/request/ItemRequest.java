package ru.practicum.shareit.request;

import lombok.*;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private Long id;

    private String description;

    private User requestor;

    private LocalDate created;
}
