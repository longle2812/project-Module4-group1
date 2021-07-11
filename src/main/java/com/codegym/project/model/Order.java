package com.codegym.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Date date;

    @OneToOne
    private Address address;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
