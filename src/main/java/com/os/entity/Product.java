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
    private Long productId;                    // product 순번

    @Column(nullable = false)
    private String productName;                // 상품명

    @Column(nullable = false)
    private int totalItems;                  // 수량

    @Column(nullable = false)
    private int price;                       // 가격

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;

}
