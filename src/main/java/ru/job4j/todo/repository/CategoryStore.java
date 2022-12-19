package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryStore {
    private final CrudRepository crudRepository;

    public List<Category> findAll() {
        return crudRepository.query("from Category", Category.class);
    }

    public Optional<Category> findById(int id) {
        return crudRepository.optional("from Category where id = :fId", Category.class,
                Map.of("fId", id));
    }

    public List<Category> findByIds(String[] ids) {
        return crudRepository.query("from Category where id  IN (:fds)",
                Category.class,
                Map.of("fds", ids));
    }
}
