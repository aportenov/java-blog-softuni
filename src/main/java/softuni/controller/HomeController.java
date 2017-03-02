
package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import softuni.entities.Article;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    @Inject
    private ArticleService articleService;

    private String redirect;

    @GetMapping("/")
    public String details(Model model){

        List<Article> articles = this.articleService.getAllArticles();
        if (articles != null){
            model.addAttribute("articles", articles);
        }

        model.addAttribute("title", "Softuni Blog");
        model.addAttribute("view", "home/index.jsp");
        redirect = "base-layout";

        return redirect;
    }
}
