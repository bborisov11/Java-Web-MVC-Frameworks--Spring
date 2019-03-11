package exodia.exodia.web.controllers;

import exodia.exodia.models.binding.UserLoginBindingModel;
import exodia.exodia.models.binding.UserRegisterBindingModel;
import exodia.exodia.models.service.UserServiceModel;
import exodia.exodia.parser.ModelParser;
import exodia.exodia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;
    private ModelParser modelParser;


    public UserController(UserService userService, ModelParser modelParser) {
        this.userService = userService;
        this.modelParser = modelParser;
    }

    @GetMapping("/register")
    public String getRegisterUser(Model model) {
        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/guest-nav");
        model.addAttribute("view", "register");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }
    @PostMapping("/register")
    public String postRegisterUser(UserRegisterBindingModel userRegisterBindingModel) {

        this.userService.createUser(this.modelParser.convert(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginUser(Model model) {
        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/guest-nav");
        model.addAttribute("view", "login");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }

    @PostMapping("/login")
    public String postLoginUser(UserLoginBindingModel userLoginBindingModel, HttpSession session) {

      UserServiceModel user = this.userService.getUserByName(userLoginBindingModel.getUsername());

        if(user == null ||
                !user.getPassword().equals(userLoginBindingModel.getPassword())) {
            return null;
        }

        session.setAttribute("user-id", user.getId());
        session.setAttribute("username", user.getUsername());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}
