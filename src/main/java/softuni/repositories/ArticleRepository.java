package softuni.repositories;


import softuni.entities.Article;
import softuni.entities.Tag;

import java.util.List;

public interface ArticleRepository {

    void save(Article article);

    List<Article> getAll();

    Article findById(Long id);

    void delete(long id);

    List<Article> findByTagName(String tagName);
}
