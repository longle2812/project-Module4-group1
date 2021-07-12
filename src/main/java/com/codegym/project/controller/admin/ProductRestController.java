package com.codegym.project.controller.admin;

import com.codegym.project.model.Brand;
import com.codegym.project.model.Category;
import com.codegym.project.model.Image;
import com.codegym.project.model.Product;
import com.codegym.project.service.brand.IBrandService;
import com.codegym.project.service.category.ICategoryService;
import com.codegym.project.service.image.IImageService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/product")
public class ProductRestController {

    @Autowired
    private IImageService imageService;
    @Autowired
    private IProductService productService;
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
    @GetMapping("/listProduct")
    public ModelAndView showListProduct(){
        ModelAndView modelAndView=new ModelAndView("/admin");
        modelAndView.addObject("products",productService.findAll());
        modelAndView.addObject("brands", brands());
        modelAndView.addObject("categories",categories());
        return modelAndView;
    }
    @GetMapping
    public ResponseEntity<Iterable<Product>>showAllProduct(){
        List<Product>products= (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findByIdProduct(@PathVariable Long id){
        Optional<Product> optionalProduct=productService.findById(id);
        if (!optionalProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProduct.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product,@RequestParam String avatarProduct){
        Image image= imageService.findImageByName(avatarProduct);
        product.setImage(image);
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        Optional<Product>optionalProduct=productService.findById(id);
        if (!optionalProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(optionalProduct.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id,@RequestBody Product product,@RequestParam String avatarProduct){
        Optional<Product>optionalProduct=productService.findById(id);
        if (!optionalProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Image image=imageService.findImageByName(avatarProduct);
        product.setImage(image);
        product.setId(optionalProduct.get().getId());
        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }
    @GetMapping("/brand")
    public ResponseEntity<Iterable<Brand>> getBrand(){
        return new ResponseEntity<>(brandService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/category")
    public ResponseEntity<Iterable<Category>> getCategory(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }
}
