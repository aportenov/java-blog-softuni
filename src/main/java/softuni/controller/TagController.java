package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import softuni.entities.Article;
import softuni.entities.Tag;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Stateless
@Controller
public class TagController {

    @Inject
    private ArticleService articleService;

    private String redirect;

    @GetMapping("/tag/{tagName}")
    public String showArticlesByTag(Model model,
                                    @PathVariable("tagName") String tagName) throws UnsupportedEncodingException {

        Tag tag = this.articleService.findTagByName(tagName);
        model.addAttribute("tag", tag);

        List<Article> articles = this.articleService.findByTagName(tagName);
        model.addAttribute("articles", articles);
        model.addAttribute("title", "Search By Tag");
        model.addAttribute("view", "articles/tag-articles.jsp");
        redirect = "base-layout";

        return redirect;
    }
}
