package com.codegym.project.controller.admin;

import com.codegym.project.model.Address;
import com.codegym.project.model.Brand;
import com.codegym.project.model.Category;
import com.codegym.project.service.address.IAddressService;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.category.ICategoryService;
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
