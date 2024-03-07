package com.os.entity;

import com.os.entity.Payment;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String amount;
    private String price;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;



}
