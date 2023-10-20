package com.Ivan.webApp_boot.web.controller;

import com.Ivan.webApp_boot.web.entity.User;
import com.Ivan.webApp_boot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class WebController {

    private final String REDIRECT_MAIN = "redirect:/users";

    @Autowired
    private UserService userService;

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "mainPage";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addUserPage";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return REDIRECT_MAIN;
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUserPage";
    }

    @PostMapping("/")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("id") int id) {
        userService.updateUser(user, id);

        return REDIRECT_MAIN;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.removeUser(id);
        return REDIRECT_MAIN;
    }
}
