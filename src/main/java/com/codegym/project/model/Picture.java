package com.codegym.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;
    private String url;

    @ManyToOne
    private Product product;


    public Picture() {
    }

    public Picture(Long id, String name, String url, Product product) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.product = product;
    }

    public Picture(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }

}
