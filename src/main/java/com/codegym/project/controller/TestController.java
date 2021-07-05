package com.codegym.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("")
    public ModelAndView test(){
        return new ModelAndView("test");
    }

    @GetMapping("/abc")
    public ModelAndView test1(){
        return new ModelAndView("abc");
    }
}
