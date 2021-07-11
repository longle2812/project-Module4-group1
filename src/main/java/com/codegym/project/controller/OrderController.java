package com.codegym.project.controller;

import com.codegym.project.model.*;
import com.codegym.project.service.address.IAddressService;
import com.codegym.project.service.cart.ICartService;
import com.codegym.project.service.item.IItemService;
import com.codegym.project.service.order.IOrderService;
import com.codegym.project.service.orderDetail.IOrderDetailService;
import com.codegym.project.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IItemService itemService;
    @PostMapping("/order/{id}")
    public ResponseEntity<Order> saveOrder(@PathVariable Long id, @RequestParam String first,
                                           @RequestParam String last, @RequestParam String country,
                                           @RequestParam String street, @RequestParam String zip, @RequestParam String city,
                                           @RequestParam String email, @RequestParam String phone) {
        Cart cart = cartService.findCartByUserId(id);
        Optional<User> user = userService.findById(id);
        List<Item> itemList = (List<Item>) itemService.findAllByCartId(cart.getId());
        Address address = new Address(country, city, street, street, zip);
        addressService.save(address);
        Order order = new Order(user.get(), new Date(), address, first, last, email, phone);
        orderService.save(order);
        double totalBill = 0;
        for (Item item: itemList){
            orderDetailService.save(new OrderDetail(order, item.getQuantity(), item.getProduct()));
            totalBill = item.getProduct().getPrice()* item.getQuantity();
        }
        order.setTotalBill(totalBill);
        orderService.save(order);
        itemService.deleteItemByCartId(cart.getId());
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
