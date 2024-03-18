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
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveProduct {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long s_prodcutId;                 // 상품임시저장 IDX

    @Column(nullable = false)
    private String s_productName;             // 상품임시저장 상품명

    @Column(nullable = false)
    private String s_productPrice;            // 상품임시저장 상품가격

    @Column(nullable = false)
    private String s_productTotalItem;        // 상품임시저장 상품수량

    ///////////////////////////////////////////////////////////////
    // 결제임시저장 IDX

    @ManyToOne
    @JoinColumn(name = "s_paymentId")
    private SavePayment savePayment;
}
