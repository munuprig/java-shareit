package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Component
public class UserInMemoryRepository implements UserRepository {
    private static Long generatorId = 0L;
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User create(User user) {
        user.setId(++generatorId);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> getById(long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public void delete(long userId) {
        if (!users.containsKey(userId)) {
            throw new EntityNotFoundException(String.format("Объект класса %s не найден", User.class));
        }
        users.remove(userId);
    }
}
