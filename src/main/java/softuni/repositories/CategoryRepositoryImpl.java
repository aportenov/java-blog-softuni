package softuni.repositories;

import softuni.entities.Category;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(CategoryRepository.class)
public class CategoryRepositoryImpl implements CategoryRepository {



    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> getAllCategories() {
        Query query = this.entityManager.createQuery("from Category");
        List<Category> categories = query.getResultList();
        return categories;
    }

    @Override
    public Category findById(long id) {
        Query query = this.entityManager.createQuery("select c from Category as c where c.id = :id");
        query.setParameter("id", id);
        Category category = null;
        if (query.getResultList().size()>0) {
            category = (Category) query.getSingleResult();
        }

        return category;
    }

    @Override
    public void save(Category category) {
        this.entityManager.persist(category);
    }

    @Override
    public void edit(long id, String name) {
        Query query = this.entityManager.createQuery("update Category as c set c.name = :name where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = this.entityManager.createQuery("delete from Category as c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}