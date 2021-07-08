package com.codegym.project.controller;

import com.codegym.project.model.Product;
import com.codegym.project.model.Review;
import com.codegym.project.model.User;
import com.codegym.project.service.product.ProductService;
import com.codegym.project.service.review.ReviewService;
import com.codegym.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReviewController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/add")
    public ResponseEntity<Review> addReview(@RequestParam String content,
                                            @RequestParam Long userId,
                                            @RequestParam int rating,
                                            @RequestParam Long productId){
        Optional<User> user = userService.findById(userId);
        Optional<Product> product= productService.findById(productId);
        Review review = new Review(product.get(), user.get(), content, rating);
        reviewService.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
}
