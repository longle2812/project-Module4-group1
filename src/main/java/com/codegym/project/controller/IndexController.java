package com.codegym.project.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/index")
    public ModelAndView showIndex(){
        String a = "";
        return new ModelAndView("index");
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        return new ModelAndView("shop");
    }
}
