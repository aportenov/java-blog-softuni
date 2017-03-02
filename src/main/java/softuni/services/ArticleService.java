package softuni.services;

import softuni.entities.Article;
import softuni.entities.Tag;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ArticleService {

    List<String> save(HttpSession session, String title, String postContent, long postCategory, String postTags, Long id);

    List<Article> getAllArticles();

    List<Tag> findTagsByName(List<String> names);

    Tag findTagByName(String name);

    Article findById(long id);

    void delete(long id);

    List<Article> findByTagName(String tagName);


}
