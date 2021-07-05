package com.codegym.project.controller;

import com.codegym.project.model.Brand;
import com.codegym.project.model.Category;
import com.codegym.project.model.Collection;
import com.codegym.project.repository.IBrandRepository;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.category.ICategoryService;
import com.codegym.project.service.collection.ICollectionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICollectionService collectionService;

    @Autowired
    private IBrandService brandService;

    @ModelAttribute("brands")
    private Iterable<Brand> brands(){
        return brandService.findAll();
    }

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @ModelAttribute("collections")
    public Iterable<Collection> collections(){
        return collectionService.findAll();
    }

    @GetMapping("/index")
    public ModelAndView showIndex(){
        return new ModelAndView("index");
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("shop");
        modelAndView.addObject("collections", collections());
        modelAndView.addObject("brands", brands());
        modelAndView.addObject("success", "a");
        modelAndView.addObject("categories", categories());
        return modelAndView;
    }
}
