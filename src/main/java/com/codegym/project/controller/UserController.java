package com.codegym.project.controller;

import com.codegym.project.model.Role;
import com.codegym.project.model.User;
import com.codegym.project.service.role.IRoleService;
import com.codegym.project.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/login")
    public ModelAndView showLoginForm(){
        return new ModelAndView("login","user", new User());
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm(){
        return new ModelAndView("register", "user", new User());
    }
    @PostMapping("/register")
    public ModelAndView saveUser(@ModelAttribute("user") User user, @RequestParam("confirmPass") String confirmPass){
        ModelAndView modelAndView = new ModelAndView("register");
        List<User> userList = (List<User>) userService.findAll();
        boolean isValidAccount = true;
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if(!matcher.matches()){
            modelAndView.addObject("error_email_input","Wrong email format");
            isValidAccount = false;
        }
        for(User u:userList){
            if(u.getEmail().equals(user.getEmail())){
                isValidAccount = false;
                modelAndView.addObject("error_email_dup","This email was already used");
                break;
            }
            if(u.getUsername().equals(user.getUsername())){
                isValidAccount = false;
                modelAndView.addObject("error_user_name_dup","This user was already claimed");
                break;
            }
        }
        if(user.getUsername().equals("")) {
            modelAndView.addObject("error_userName", "User name can not be null");
            isValidAccount = false;
        }
        if(user.getPassword().length()<6){
            modelAndView.addObject("error_passWord", "Password can not be less then 6 character");
            isValidAccount = false;
        }
        if(!user.getPassword().equals(confirmPass)){
            modelAndView.addObject("error_pass_input", "Confirm pass does not match");
            isValidAccount = false;
        }
        if(isValidAccount){
            Optional<Role> role = roleService.findById(1L);
            user.getRoles().add(role.get());
            User user1 = user;
            userService.save(user);
            return new ModelAndView("login","user",new User());
        }
        return modelAndView;
    }
}
