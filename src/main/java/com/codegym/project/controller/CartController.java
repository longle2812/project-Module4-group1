package com.codegym.project.controller;

import com.codegym.project.model.Cart;
import com.codegym.project.model.Item;
import com.codegym.project.model.Product;
import com.codegym.project.service.cart.ICartService;
import com.codegym.project.service.item.IItemService;
import com.codegym.project.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IItemService itemService;
    @Autowired
    private IProductService productService;

    @GetMapping("/cart")
    public ResponseEntity<Integer> countCartByUser(@RequestParam Long userId) {
        Cart cart = cartService.findCartByUserId(userId);
        int itemNumber = itemService.countAllByCartId(cart.getId());
        return new ResponseEntity<>(itemNumber, HttpStatus.OK);
    }

    @GetMapping("/cart/items")
    public ResponseEntity<Iterable<Item>> findItemByUser(@RequestParam Long userId) {
        Cart cart = cartService.findCartByUserId(userId);
        List<Item> items = (List<Item>) itemService.findAllByCartId(cart.getId());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<Item> addItemToCart(@RequestParam Long userId, @RequestParam Long productId) {
        Cart cart = cartService.findCartByUserId(userId);
        Optional<Product> product = productService.findById(productId);
        Optional<Item> item = itemService.findItemByProductIdAndCartId(productId, cart.getId());
        if (item.isPresent()){
            item.get().setQuantity(item.get().getQuantity()+1);
            itemService.save(item.get());
            return new ResponseEntity<>(item.get(),HttpStatus.CREATED);
        }
        else {
            Item newItem = new Item(1L, product.get(), cart);
            itemService.save(newItem);
            return new ResponseEntity<>(newItem, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/cart/remove")
    public ResponseEntity<Item> removeItemFromCart(@RequestParam Long userId, @RequestParam Long productId){
        Cart cart = cartService.findCartByUserId(userId);
        Optional<Item> item = itemService.findItemByProductIdAndCartId(productId, cart.getId());
        if (item.isPresent()){
            itemService.remove(item.get().getId());
            return new ResponseEntity(item.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
