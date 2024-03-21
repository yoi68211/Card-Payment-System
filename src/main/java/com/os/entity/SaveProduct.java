package com.os.entity;

import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveProduct {

    @Id
    @Column(nullable = false, name = "s_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                 // 상품임시저장 IDX


    private String s_productName;             // 상품임시저장 상품명


    private String s_productPrice;            // 상품임시저장 상품가격


    private String s_productTotalItem;        // 상품임시저장 상품수량

    @ManyToOne
    @JoinColumn(name = "s_payment_id", referencedColumnName = "s_payment_id",nullable = false)
    private SavePayment savePayment;
}
