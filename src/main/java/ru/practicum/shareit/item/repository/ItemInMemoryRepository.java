package ru.practicum.shareit.item.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Primary
public class ItemInMemoryRepository implements ItemRepository {
    private static Long genId = 0L;
    private final Map<Long, Item> items = new HashMap<>();
    private final Map<Long, List<Item>> itemsByUsers = new LinkedHashMap<>();


    @Override
    public Item create(Item item, long userId) {
        item.setId(++genId);
        item.setOwnerId(userId);
        items.put(item.getId(), item);
        final List<Item> itemsByUser = itemsByUsers.computeIfAbsent(item.getOwnerId(), k -> new ArrayList<>());
        itemsByUser.add(item);
        return item;
    }

    @Override
    public Optional<Item> getByID(Long itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public List<Item> getItemByUser(Long id) {
        return itemsByUsers.get(id);
    }

    @Override
    public List<Item> getItemBySearch(String text) {
        return items.values().stream()
                .filter(item -> item.getAvailable() && ((item.getName().toLowerCase().contains(text.toLowerCase())) ||
                        (item.getDescription().toLowerCase().contains(text.toLowerCase()))))
                .collect(Collectors.toList());
    }
}
