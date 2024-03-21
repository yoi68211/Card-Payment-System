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
    @Column(nullable = false, name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // product IDX

    @Column(nullable = false)
    private String productName;                // 상품 명

    @Column(nullable = false)
    private int productTotalItems;                  // 상품 수량

    @Column(nullable = false)
    private int productPrice;                       // 상품 가격

    @Column(nullable = false)
    private int productAmount;                       // 상품 총액

    ///////////////////////////////////////////////////////
    // 결제 IDX

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id",nullable = false)
    private Payment payment;





}
