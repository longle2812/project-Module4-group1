package com.codegym.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    private Long quantity;
    @OneToOne
    private Product product;

    public OrderDetail(Order order, Long quantity, Product product) {
        this.order = order;
        this.quantity = quantity;
        this.product = product;
    }

    public OrderDetail() {
    }
}