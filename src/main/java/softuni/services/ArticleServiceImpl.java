package softuni.services;

import softuni.validation.Validator;
import softuni.entities.Article;
import softuni.entities.Category;
import softuni.entities.Tag;
import softuni.entities.UserModel;
import softuni.repositories.ArticleRepository;
import softuni.repositories.CategoryRepository;
import softuni.repositories.TagRepository;
import softuni.repositories.UserRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Stateless
@Local(ArticleService.class)
public class ArticleServiceImpl implements  ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Inject
    private TagService tagService;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private TagRepository tagRepository;

    @Inject
    private Validator validator;

    @Override
    public List<String> save(HttpSession session, String title, String postContent, long postCategory, String postTags, Long id) {
        Article article = new Article();
         List<String> tagStr = Arrays.asList(postTags.split("[\\s,]+"));
        Set<Tag> tags = this.tagService.setArticleTags(tagStr);
        article.setTags(tags);
        article.setTitle(title);
        article.setContent(postContent);
        Category category = this.categoryRepository.findById(postCategory);
        article.setCategory(category);
        long creator = (long) session.getAttribute("userId");
        UserModel userModel = this.userRepository.findById((long) session.getAttribute("userId"));
        article.setUser(userModel);
        List<String> errors = this.validator.validate(article);
        if (errors.isEmpty()) {
            Article newArticle = this.articleRepository.findById(id);
            article.setTags(tags);
            article.setTitle(title);
            article.setContent(postContent);
            this.articleRepository.save(article);
        }

        return errors;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = this.articleRepository.getAll();
        return articles;
    }

    @Override
    public List<Tag> findTagsByName(List<String> names) {
        List<Tag> tags = this.tagRepository.findTagByNames(names);
        return tags;
    }

    @Override
    public Tag findTagByName(String name) {
       return this.tagRepository.findByName(name);
    }

    @Override
    public Article findById(long id) {
        return this.articleRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        this.articleRepository.delete(id);
    }

    @Override
    public List<Article> findByTagName(String tagName) {
        List<Article> articles = this.articleRepository.findByTagName(tagName);
        return articles;
    }


}
