package com.codegym.project.controller;

import com.codegym.project.model.Cart;
import com.codegym.project.model.Item;
import com.codegym.project.service.cart.ICartService;
import com.codegym.project.service.item.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IItemService itemService;

    @GetMapping("/cart")
    public ResponseEntity<Integer> countCartByUser(@RequestParam Long userId){
        Cart cart = cartService.findCartByUserId(userId);
        int itemNumber = itemService.countAllByCartId(cart.getId());
        return new ResponseEntity<>(itemNumber, HttpStatus.OK);
    }

    @GetMapping("/cart/items")
    public ResponseEntity<Iterable<Item>> findItemByUser(@RequestParam Long userId){
        Cart cart = cartService.findCartByUserId(userId);
        List<Item> items = (List<Item>) itemService.findAllByCartId(cart.getId());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
