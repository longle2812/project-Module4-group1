package com.codegym.project.controller;

import com.codegym.project.model.*;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.category.ICategoryService;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.picture.IPictureService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
    private IPictureService pictureService;

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
        modelAndView.addObject("categories", categories());
        modelAndView.addObject("products", productService.findAllPage(pageable));
        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        List<Picture> pictures = (List<Picture>) pictureService.findPictureByProductId(id);
        if (product.isPresent()){
            ModelAndView modelAndView =  new ModelAndView("/product/product", "product", product.get());
            modelAndView.addObject("collections", collections());
            modelAndView.addObject("brands", brands());
            modelAndView.addObject("categories", categories());
            modelAndView.addObject("main_picture", pictures.get(0));
            pictures.remove(0);
            modelAndView.addObject("sub_pictures", pictures);
            return modelAndView;
        }
        return new ModelAndView("error-404");
    }

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("/product/product");
    }

}
