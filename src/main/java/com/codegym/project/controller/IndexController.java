package com.codegym.project.controller;

import com.codegym.project.model.Brand;
import com.codegym.project.model.Category;
import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.category.ICategoryService;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICollectionService collectionService;

    @Autowired
    private IBrandService brandService;

    @ModelAttribute("products")
    private Iterable<Product> products(){
        return productService.findAll();
    }

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
        String a = "";
        return new ModelAndView("index");
    }

    @GetMapping("/shop")
    public ModelAndView showShop(@PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/product/shop");
        modelAndView.addObject("collections", collections());
        modelAndView.addObject("brands", brands());
        modelAndView.addObject("success", "a");
        modelAndView.addObject("categories", categories());
        modelAndView.addObject("products", productService.findAllPage(pageable));
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("/product/product");
    }

}
