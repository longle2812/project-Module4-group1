package com.codegym.project.controller;

import com.codegym.project.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @PostMapping("/order/{id}")
    public ResponseEntity<Order> saveOrder(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
