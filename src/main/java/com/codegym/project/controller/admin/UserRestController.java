package com.codegym.project.controller.admin;

import com.codegym.project.model.Address;
import com.codegym.project.model.Image;
import com.codegym.project.model.User;
import com.codegym.project.service.address.IAddressService;
import com.codegym.project.service.image.IImageService;
import com.codegym.project.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/user")
public class UserRestController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IImageService imageService;
    @GetMapping
    public ResponseEntity<Iterable<User>> showListUser(){
        List<User>users= (List<User>) userService.findAll();
        if (users.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/listUser")
    public ModelAndView showAllUser(){
        ModelAndView modelAndView=new ModelAndView("/admin/user/listUser");
        modelAndView.addObject("users",userService.findAll());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findByIdUser(@PathVariable Long id){
        Optional<User>userOptional=userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user, @RequestParam String avatarName){
        Image image = imageService.findImageByName(avatarName);
        user.setAvatar(image);
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        Optional<User>userOptional=userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(userOptional.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id,@RequestBody User user){
        Optional<User>userOptional=userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }
}
