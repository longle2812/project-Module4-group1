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

    public Order() {
    }

    public Order(User user, Date date, Address address, String firstName, String lastName, String email, String phoneNumber) {
        this.user = user;
        this.date = date;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @OneToOne
    private Address address;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private double totalBill;
}
