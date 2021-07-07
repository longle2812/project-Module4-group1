package com.codegym.project.controller;

import com.codegym.project.model.Blog;
import com.codegym.project.model.User;
import com.codegym.project.service.blog.IBlogService;
import com.codegym.project.service.jwt.JwtService;
import com.codegym.project.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/blogs")
    public ModelAndView showBlogList(){
        Page<Blog> blogs = blogService.findAll(PageRequest.of(0,4, Sort.by("date").descending()));
        return new ModelAndView("blog","blogs", blogs);
    }
    @GetMapping("/blogs/create")
    public ModelAndView showBlogCreateForm(){
        return new ModelAndView("create-blog");
    }
    @PostMapping("/blogs/create/{token}")
    public ResponseEntity<Blog> saveBlog(@PathVariable String token, @RequestBody Blog blog){
        String userName = jwtService.getUserNameFromJwtToken(token);
        Optional<User> user = userService.findByUsername(userName);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            blog.setAuthor(user.get());
            blog.setDate(new Date());
            return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
        }
    }
}
