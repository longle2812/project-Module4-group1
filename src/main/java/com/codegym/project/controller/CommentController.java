package com.codegym.project.controller;

import com.codegym.project.exception.NotFoundException;
import com.codegym.project.model.Blog;
import com.codegym.project.model.Comment;
import com.codegym.project.service.blog.IBlogService;
import com.codegym.project.service.comment.ICommentService;
import com.codegym.project.service.jwt.JwtService;
import com.codegym.project.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/comments/create/{token}")
    public ResponseEntity<Comment>  createComment(@PathVariable String token, @RequestBody Comment comment)  {
        String userName = jwtService.getUserNameFromJwtToken(token);
        if(userName==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!commentService.containBadWord(comment)){
            comment.setUser(userService.findByUsername(userName).get());
            comment.setDate(new Date());
            return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/comments/getAll/{blogId}")
    public ResponseEntity<List<Comment>> findAll(@PathVariable Long blogId) {
        Optional<Blog> blogOptional = blogService.findById(blogId);
        if(!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            List<Comment> comments = (List<Comment>) commentService.findByBlog(blogOptional.get());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }
}
