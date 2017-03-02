package softuni.repositories;

import softuni.entities.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getAllCategories();

    Category findById(long id);

    void save(Category category);

    void edit(long id, String name);

    void delete(long id);
}
