package softuni.services;


import softuni.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category findById(long id);

    void save(Category categoryDto);

    void edit(long id, String name);

    void delete(long id);
}

