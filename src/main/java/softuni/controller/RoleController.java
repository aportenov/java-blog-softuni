package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.entities.Category;
import softuni.entities.UserModel;
import softuni.services.CategoryService;
import softuni.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class RoleController {

    @Inject
    private CategoryService categoryService;

    @Inject
    private UserService userService;

    @GetMapping("/admin/categories")
    public String changeCategoriesPage(Model model) {
        List<Category> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Categories Admin Page");
        model.addAttribute("view", "admin/categories/categories-page.jsp");

        return "base-layout";
    }

    @GetMapping("/admin/categories/edit/{id}")
    public String editCategoryPage(@PathVariable("id") long id, Model model) {
        Category category = this.categoryService.findById(id);
        model.addAttribute("category", category);
        model.addAttribute("title", "Edit Category");
        model.addAttribute("view", "admin/categories/edit-category.jsp");

        return "base-layout";
    }

    @PostMapping("/admin/categories/edit/{id}")
    public String editCategoryAction(@PathVariable("id") long id,
                                     @RequestParam("name") String name) {

        this.categoryService.edit(id, name);


        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategoryPage(Model model) {

        model.addAttribute("title", "Add Category");
        model.addAttribute("view", "admin/categories/add-category.jsp");

        return "base-layout";
    }

    @PostMapping("/admin/categories/add")
    public String addCategory(@RequestParam("name") String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        this.categoryService.save(category);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id) {
        this.categoryService.delete(id);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/users")
    public String showUsersPage(Model model) {
        List<UserModel> users = this.userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("title", "Users");
        model.addAttribute("view", "admin/users/users-page.jsp");
        return "base-layout";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String showUsersEditPage(@PathVariable("id") long id, Model model) {
        UserModel userModel = this.userService.findById(id);
        model.addAttribute("user", userModel);
        model.addAttribute("adminCheck", userModel.isAdmin() ? "checked" : "");
        model.addAttribute("userCheck", "checked");
        model.addAttribute("title", "Edit User");
        model.addAttribute("view", "admin/users/edit-user.jsp");
        return "base-layout";
    }

    @PostMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable("id") long id,
                           @RequestParam("user-fullname") String fullName,
                           @RequestParam("user-password-first") String password,
                           @RequestParam("user-password-second") String confirmPassword,
                           @RequestParam("admin") String isAdmin,
                           @RequestParam("user") String isUser,
                           Model model) {
        List<String> errors = new ArrayList<>();
        UserModel userModel = this.userService.findById(id);

        if (!password.equals(confirmPassword)) {
            errors.add("Password do not match!");
        }

        if (errors.isEmpty()) {
            userModel.setFullName(fullName);
            userModel.setPassword(password);
            this.userService.updateRoles(userModel, isAdmin, isUser);
            this.userService.createUser(userModel);
            return "redirect:/admin/users";
        } else {
            model.addAttribute("user", userModel);
            model.addAttribute("errors", errors);
            model.addAttribute("title", "Edit User");
            model.addAttribute("view", "admin/users/edit-user.jsp");
        }

        return "base-layout";
    }
}

