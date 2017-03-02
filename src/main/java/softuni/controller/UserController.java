package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.entities.Role;
import softuni.entities.UserModel;
import softuni.services.UserService;
import softuni.validation.Validator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest  request;

    @Inject
    private Validator validator;

    private String redirect;


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("title", "Register Page");
        model.addAttribute("view", "user/register.jsp");
        redirect = "base-layout";

        return redirect;
    }

    @PostMapping("/register")
    public String registerUser(Model model,
                               @RequestParam("user-email") String email,
                               @RequestParam("user-fullname") String fullName,
                               @RequestParam("user-password-first") String password,
                               @RequestParam("user-password-second") String confirmPassword
    ) {

        List<String> errors = new ArrayList<>();
        UserModel userModel = new UserModel(fullName, email, password);
        userModel.addRole(new Role("ADMIN"));
        errors.addAll(this.validator.validate(userModel));

        if (!password.equals(confirmPassword)) {
            errors.add("Password do not match!");
        }


        if (!this.userService.isAvailable(email)) {
            errors.add(String.format("Username with email %s already exists!", email));
        }


        if (errors.isEmpty()) {
            this.userService.createUser(userModel);
            redirect = "redirect:/login";
        } else {
            model.addAttribute("title", "Register page");
            model.addAttribute("errors", errors);
            model.addAttribute("view", "user/register.jsp");
            redirect = "base-layout";
        }

        return redirect;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("title", "Login Page");
        model.addAttribute("view", "user/login.jsp");
        redirect = "base-layout";

        return  redirect;
    }

    @PostMapping("/login")
    public String logUser(Model model,
                          HttpSession session,
                          @RequestParam("user-email") String email,
                          @RequestParam("user-password") String password

    ) {
        String redirect = null;
        List<String> errors = new ArrayList<>();
        UserModel userModel = this.userService.findUserByEmail(email);
        if (userModel == null){
            errors.add(String.format("Invalid username or password"));
        }

        if (errors.isEmpty()) {
            session.setAttribute("userId", userModel.getId());
            session.setAttribute("username", userModel.getFullName());
            session.setAttribute("userEmail", userModel.getEmail());
            session.setAttribute("admin", userModel.isAdmin());
            session.setAttribute("userRole", userModel.getRoles());
            redirect = "redirect:/";
        } else {
            model.addAttribute("title", "Login page");
            model.addAttribute("errors", errors);
            model.addAttribute("view", "user/login.jsp");
            redirect = "base-layout";
        }

        return redirect;


    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        model.addAttribute("title", "My Profile");
        model.addAttribute("view", "user/profile.jsp");

        return "base-layout";
    }

    @PostMapping("/profile")
    public String updateUser(Model model,
                               HttpSession session,
                               @RequestParam("user-email") String email,
                               @RequestParam("user-fullname") String fullName,
                               @RequestParam("user-password-first") String password,
                               @RequestParam("user-password-second") String confirmPassword
    ) {
        List<String> errors = new ArrayList<>();
        if (!password.equals(confirmPassword)) {
            errors.add("Password do not match!");
        }

        if (errors.isEmpty()) {
            Long userId = (Long) session.getAttribute("userId");
            UserModel userModel = this.userService.findById(userId);
            userModel.setEmail(email);
            userModel.setFullName(fullName);
            userModel.setPassword(password);
            this.userService.updateUser(userModel);
            redirect = "redirect:/profile";
        } else {
            model.addAttribute("title", "Profile page");
            model.addAttribute("errors", errors);
            model.addAttribute("view", "user/profile.jsp");
            redirect = "base-layout";
        }

        return redirect;
    }



    @GetMapping("/logout")
    public String logOutPage(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("title", "Softuni Blog");
        model.addAttribute("view", "home/index.jsp");
        redirect = "base-layout";

        return  redirect;
    }

}
