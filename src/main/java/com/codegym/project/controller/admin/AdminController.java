package com.codegym.project.controller.admin;

import com.codegym.project.model.*;
import com.codegym.project.service.address.IAddressService;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.category.ICategoryService;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.color.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAddressService addressService;
    @ModelAttribute("address")
    public Iterable<Address> addresses(){
        return addressService.findAll();
    }
    @Autowired
    private ICollectionService collectionService;
    @ModelAttribute("collections")
    public Iterable<Collection> collections(){
        return collectionService.findAll();
    }
    @Autowired
    private IColorService colorService;
    @ModelAttribute("colors")
    public Iterable<Color> colors(){
        return colorService.findAll();
    }
    @Autowired
    private IBrandService brandService;
    @ModelAttribute("brands")
    public Iterable<Brand>brands(){
        return brandService.findAll();
    }
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }
    @GetMapping("")
    public ModelAndView admin(){
        return new ModelAndView("/admin");
    }


}
