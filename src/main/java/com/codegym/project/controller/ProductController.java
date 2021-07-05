package com.codegym.project.controller;

import com.codegym.project.model.Product;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("products")
    public Iterable<Product> products(){
        return productService.findAll();
    }

}
