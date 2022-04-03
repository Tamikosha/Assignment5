package com.example.AtmSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "сard_number")
    public String cardNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "card_expiry_date")
    public String cardExpiryDate;
    @Column(name = "cvv")
    private int CVV;
    @Column(name = "money")
    private int money;
}
