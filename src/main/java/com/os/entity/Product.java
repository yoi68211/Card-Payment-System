package com.os.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String totalItems;
    private String amount;
    private String price;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;


}
