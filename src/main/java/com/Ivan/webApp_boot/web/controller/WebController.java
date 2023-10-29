package com.Ivan.webApp_boot.web.controller;

import com.Ivan.webApp_boot.web.entity.User;
import com.Ivan.webApp_boot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class WebController {

    private final String REDIRECT_MAIN = "redirect:/users";

    private UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "mainPage";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") int id) {
        if (id > 0) {
            model.addAttribute("user", userService.getUserById(id));
        } else {
            model.addAttribute("user", new User());
        }
        return "editPage";
    }

    @PostMapping("/edit")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam("id") int id,
                           Model model) {
        if (bindingResult.hasErrors()) {
            if (id > 0) {
                user.setId(id);

            }
            model.addAttribute("user", user);
            return "editPage";
        }

        if (user.getId() == 0) {
            userService.add(user);
        } else {
            userService.updateUser(user, id);
        }

        return REDIRECT_MAIN;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.removeUser(id);
        return REDIRECT_MAIN;
    }
}
