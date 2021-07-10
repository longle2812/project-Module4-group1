package com.codegym.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }
}
