package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.entities.Article;
import softuni.entities.Category;
import softuni.services.ArticleService;
import softuni.services.CategoryService;
import softuni.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class AritcleController {

    @Inject
    private CategoryService categoryService;

    @Inject
    private ArticleService articleService;

    @Inject
    private UserService userService;

     private String redirect;

    @GetMapping("/articles/create")
    public String showCreateArticlePage(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null) {
            List<Category> categories = this.categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("title", "Create Article");
            model.addAttribute("view", "articles/create-article.jsp");
            redirect = "base-layout";
        } else {
            model.addAttribute("title", "Softuni Blog");
            model.addAttribute("view", "home/index.jsp");
            redirect = "redirect:/";
        }

        return redirect;
    }

    @PostMapping("/articles/create")
    public String createArticle(Model model,
                                HttpSession session,
                                @RequestParam("post-title") String title,
                                @RequestParam("post-content") String postContent,
                                @RequestParam("post-category") long postCategory,
                                @RequestParam("post-tags") String postTags) {

        List<String> errors = this.articleService.save(session, title, postContent, postCategory, postTags, null);
        if (! errors.isEmpty()) {
            List<Category> categories = this.categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("title", "Create Article");
            model.addAttribute("errors", errors);
            model.addAttribute("view", "articles/create-article.jsp");
        } else {
            model.addAttribute("title", "Softuni Blog");
            model.addAttribute("view", "home/index.jsp");
        }

        redirect = "base-layout";

        return redirect;
    }

    @GetMapping("/article/{id}")
    public String getSingleArticlePage(@PathVariable("id") long id,
                                       Model model) {
        Article article = this.articleService.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("view", "articles/single-article.jsp");
        redirect = "base-layout";

        return redirect;
    }

    @GetMapping("/article/edit/{id}")
    public String showEditArticlePage(Model model,
                                      @PathVariable("id") long id) {
        List<Category> categories = this.categoryService.getAllCategories();
        Article article = this.articleService.findById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("article", article);
        model.addAttribute("title", "Edit Article");
        model.addAttribute("view", "articles/edit-article.jsp");
        redirect = "base-layout";

        return redirect;
    }

    @PostMapping("/article/edit/{id}")
    public String editArticle(Model model,
                              @PathVariable("id") long id,
                              HttpSession session,
                              @RequestParam("post-title") String title,
                              @RequestParam("post-content") String postContent,
                              @RequestParam("post-category") long postCategory,
                              @RequestParam("post-tags") String postTags) {


        List<String> errors = this.articleService.save(session, title, postContent, postCategory, postTags, id);
        if (! errors.isEmpty()) {
            model.addAttribute("errors", errors);
            List<Category> categories = this.categoryService.getAllCategories();
            Article article = this.articleService.findById(id);
            model.addAttribute("categories", categories);
            model.addAttribute("article", article);
            model.addAttribute("title", "Edit Article");
            model.addAttribute("view", "articles/edit-article.jsp");
        }else {
            model.addAttribute("title", "Softuni Blog");
            model.addAttribute("view", "home/index.jsp");
        }

        redirect = "base-layout";

        return redirect;
    }

    @GetMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable("id") long id) {
        this.articleService.delete(id);
        redirect = "redirect:/";

        return redirect;
    }
}
