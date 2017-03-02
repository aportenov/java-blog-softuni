package softuni.repositories;

import softuni.entities.Article;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateless
@Local(ArticleRepository.class)
public class ArticleRepositoryImpl implements  ArticleRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Article article) {
        this.entityManager.persist(article);
    }

    @Override
    public List<Article> getAll() {
        Query query = this.entityManager.createQuery("SELECT a FROM Article AS a");
        List<Article> articles = null;
        if (query.getResultList().size() > 0){
            articles = query.getResultList();
        }else {
            articles = new ArrayList<>();
        }


        return Collections.unmodifiableList(articles);
    }

    @Override
    public Article findById(Long id) {
        Article article = null;
        Query query = this.entityManager.createQuery("SELECT a FROM Article AS a WHERE a.id = :id");
        query.setParameter("id", id);
        if (query.getResultList().size() > 0){
            article = (Article) query.getSingleResult();
        }

        return article;
    }

    @Override
    public void delete(long id) {
        Query query = this.entityManager.createQuery("delete from Article as a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Article> findByTagName(String tagName) {
        List<Article> articles = null;
        Query query = this.entityManager.createQuery("select a from Article as a " +
                "inner join a.tags as t " +
                "where t.name = :name");
        query.setParameter("name", tagName);
        if (query.getResultList().size() > 0){
            articles = query.getResultList();
        }else {
            articles = new ArrayList<>();
        }

        return Collections.unmodifiableList(articles);
    }
}
