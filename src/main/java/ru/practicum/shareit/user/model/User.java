package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Pattern(regexp = "^\\S*$", message = "Логин не может содержать пробелы.")
    private String name;

    @NotBlank(message = "Электронная почта не может быть пустым.")
    @Email(message = "Электронная почта должна содержать символ @.")
    private String email;
}
