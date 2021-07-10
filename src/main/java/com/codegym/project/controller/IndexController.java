package com.codegym.project.controller;

import com.codegym.project.model.*;
import com.codegym.project.repository.IItemRepository;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.cart.ICartService;
import com.codegym.project.service.category.ICategoryService;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.item.IItemService;
import com.codegym.project.service.picture.IPictureService;
import com.codegym.project.service.product.IProductService;
import com.codegym.project.service.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private IReviewService reviewService;
    @Autowired
    private IItemService itemService;
    @Autowired
    private ICartService cartService;

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
    public ModelAndView showShop(@PageableDefault Pageable pageable) {
        pageable = PageRequest.of(0, 6, Sort.by("name").ascending());
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
            Image main_picture = product.get().getImage();
            if (pictures.isEmpty()){
                pictures.add(pictureService.findPictureByName("no-picture.png"));
                pictures.add(pictureService.findPictureByName("no-picture.png"));
            }
            modelAndView.addObject("main_picture", main_picture);
            modelAndView.addObject("sub_pictures", pictures);
            modelAndView.addObject("review_number", reviewService.countReviewByProductId(id));
            modelAndView.addObject("reviews", reviewService.findReviewByProductId(id));
            return modelAndView;
        }
        return new ModelAndView("error-404");
    }

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("/product/product");
    }

    @GetMapping("/shop/cart")
    public ModelAndView showCart(){
        return new ModelAndView("/product/shopping-cart");
    }
}
