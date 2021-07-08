package com.codegym.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    private String content;
    private int rating;


    public Review(Product product, User user, String content, int rating) {
        this.product = product;
        this.user = user;
        this.content = content;
        this.rating = rating;
    }
}
