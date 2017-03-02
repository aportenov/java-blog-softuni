package softuni.services;

import softuni.entities.Category;
import softuni.repositories.CategoryRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local(CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = this.categoryRepository.getAllCategories();

        return categories;
    }

    @Override
    public Category findById(long id) {
        Category category = this.categoryRepository.findById(id);

        return category;
    }

    @Override
    public void save(Category category) {
        this.categoryRepository.save(category);
    }


    @Override
    public void edit(long id, String name) {
        this.categoryRepository.edit(id, name);
    }

    @Override
    public void delete(long id) {
        this.categoryRepository.delete(id);
    }

}
