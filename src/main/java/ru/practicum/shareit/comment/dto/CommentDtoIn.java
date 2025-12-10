package ru.practicum.shareit.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.practicum.shareit.util.Create;
import ru.practicum.shareit.util.Update;

@Data
public class CommentDtoIn {
    @Size(max = 1000, groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class})
    private String text;
}
