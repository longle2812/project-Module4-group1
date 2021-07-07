package com.codegym.project.controller.admin;

import com.codegym.project.model.Brand;
import com.codegym.project.model.Product;
import com.codegym.project.service.brand.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/brand")
public class BrandRestController {
    @Autowired
    private IBrandService brandService;
    @GetMapping("/listBrand")
    public ModelAndView showListBrand(){
        ModelAndView modelAndView=new ModelAndView("/admin/brand/listBrand");
        modelAndView.addObject("brands",brandService.findAll());
        return modelAndView;
    }
    @GetMapping
    public ResponseEntity<Iterable<Brand>> showAllBrand(){
        List<Brand>brands= (List<Brand>) brandService.findAll();
        if (brands.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brands,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Brand> findByIdBrand(@PathVariable Long id){
        Optional<Brand> optionalBrand=brandService.findById(id);
        if (!optionalBrand.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       return new ResponseEntity<>(optionalBrand.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Brand>createBrand(@RequestBody Brand brand){
        return new ResponseEntity<>(brandService.save(brand),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Brand>deleteBrand(@PathVariable Long id){
        Optional<Brand> optionalBrand=brandService.findById(id);
        if (!optionalBrand.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.remove(id);
        return new ResponseEntity<>(optionalBrand.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Brand>editBrand(@PathVariable Long id,@RequestBody Brand brand){
        Optional<Brand> optionalBrand=brandService.findById(id);
        if (!optionalBrand.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brand.setId(optionalBrand.get().getId());
        return new ResponseEntity<>(brandService.save(brand),HttpStatus.OK);
    }
}
