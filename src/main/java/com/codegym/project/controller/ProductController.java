package com.codegym.project.controller;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.repository.ICollectionRepository;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICollectionService collectionService;

    @ModelAttribute("products")
    public Iterable<Product> products(){
        return productService.findAll();
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<Iterable<Product>> findProductByCollection(@PathVariable Long id){
        Collection collection = collectionService.findCollectionById(id);
        Iterable<Product> products = productService.findProductByCollection(collection);
        if (products != null){
            return  new ResponseEntity<> (products,HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

}
