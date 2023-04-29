package kz.bitlab.Lesson5.services;

import kz.bitlab.Lesson5.entity.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category findById(Long id);

    void deleteById(Long id);
}
