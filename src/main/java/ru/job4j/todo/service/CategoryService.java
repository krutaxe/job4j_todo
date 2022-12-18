package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryStore;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryStore categoryStore;

    public List<Category> findAll() {
        return categoryStore.findAll();
    }

    public List<Category> findById(String[] ids) {
        List<Category> categoryList = new ArrayList<>();
        if (ids != null) {
            for (String i : ids) {
                int j = Integer.parseInt(i);
                categoryStore.findById(j).ifPresent(categoryList::add);
            }
        }
        return categoryList;
    }

}
