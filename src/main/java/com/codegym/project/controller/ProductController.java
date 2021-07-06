package com.codegym.project.controller;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IBrandService brandService;

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

    @GetMapping("/brand")
    public ResponseEntity<Iterable<Product>> findProductByBrand(@RequestParam(name = "min") double min, @RequestParam(name = "max") double max,
                                                                @RequestParam(name = "brandIds",required = false) Optional<Set<Long>> brandIds){
        if (!brandIds.isPresent()){
            brandIds = Optional.ofNullable(brandService.getAllIds());
        }

        Iterable<Product> products = productService.findProductByBrandIdsAndPrice(brandIds.get(), min, max);
        if (products.iterator().hasNext()){
            return  new ResponseEntity<> (products,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
