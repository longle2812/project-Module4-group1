package com.codegym.project.controller;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.collection.ICollectionService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
    public Iterable<Product> products() {
        return productService.findAll();
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<Iterable<Product>> findProductByCollection(@PathVariable Long id) {
        List<Product> products = (List<Product>) productService.findProductByCollectionIds(id);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/brand")
    public ResponseEntity<Iterable<Product>> findProductByBrand(@RequestParam(name = "min") double min, @RequestParam(name = "max") double max,
                                                                @RequestParam(name = "brandIds", required = false) Optional<Set<Long>> brandIds,
                                                                @RequestParam(name = "number") int number,
                                                                @RequestParam(name = "sort") String sort,
                                                                Pageable pageable) {
        if (!brandIds.isPresent()) {
            brandIds = Optional.ofNullable(brandService.getAllIds());
        }
        switch (sort) {
            case "acs":
                pageable = PageRequest.of(0, number, Sort.by("name").ascending());
                break;
            case "desc":
                pageable = PageRequest.of(0, number, Sort.by("name").descending());
                break;
            case "low":
                pageable = PageRequest.of(0, number, Sort.by("price").ascending());
                break;
            case "high":
                pageable = PageRequest.of(0, number, Sort.by("price").descending());
                break;
        }

        Page<Product> products = productService.findProductByBrandIdsAndPrice(brandIds.get(), min, max, pageable);
        if (products.hasContent()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> findProductByName(@RequestParam String keyword) {
        if (!keyword.equals("")) {
            Iterable<Product> products = productService.findProductByNameContaining(keyword);
            if (products.iterator().hasNext()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Product>> findProductByCategory(@PathVariable String category, Pageable pageable){
        pageable = PageRequest.of(0,6);
        Page<Product> products = productService.findProductByCategory(category,pageable);
        if (products.hasContent()){
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Page<Product>> findProductByColor(@PathVariable String color, Pageable pageable){
        pageable = PageRequest.of(0,6);
        Page<Product> products = productService.findProductByColor(color,pageable);
        if (products.hasContent()){
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
