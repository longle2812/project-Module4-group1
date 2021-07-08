package com.codegym.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @ManyToOne
        private Brand brand;
        @ManyToOne
        private Category category;
        private double price;
        @OneToOne
        private Image image;
        @ManyToOne
        private Collection collection;
        @OneToOne
        private Color color;

        private String description;

}
