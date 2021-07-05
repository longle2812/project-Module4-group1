package com.codegym.project.controller;

import com.codegym.project.model.User;
import com.codegym.project.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ModelAndView showLoginForm(){
        return new ModelAndView("/login","user", new User());
    }
}
