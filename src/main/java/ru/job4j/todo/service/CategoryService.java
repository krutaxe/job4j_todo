package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryStore;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryStore categoryStore;

    public List<Category> findAll() {
        return categoryStore.findAll();
    }

    public List<Category> findByIds(String[] ids) {
        Integer[]id = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            id[i] = Integer.parseInt(ids[i]);
        }
       return categoryStore.findByIds(id);
    }

}
