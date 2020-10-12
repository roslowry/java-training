package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signup(Model model, User user){
        return "signup";
    }

    @PostMapping()
    public String signUpNewUser(Model model, User user ){
        System.out.println("user.toString() :   " + user.toString());
        if (userService.isUsernameAvailable(user.getUsername())) {
            userService.createUser(user);
            return "login";
        } else {
            model.addAttribute("success", false);
            return "signup";
        }
    }
}
