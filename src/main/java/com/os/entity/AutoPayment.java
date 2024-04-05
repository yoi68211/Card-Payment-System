package com.os.entity;

import com.os.dto.InsertDTO;
import com.os.util.AutoStatus;
import com.os.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoPayment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "auto_id")
    private Long id;                              // 자동결제 IDX

/*
    @Column(nullable = false)
    private String autoFirstpay;                        // 자동결제 첫결제금액
*/


    private String billingKey;
    private LocalDate autoPayDate;               // 결제된 날짜
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AutoStatus autoStatus;                          // 자동결제 상태

    @Column(nullable = false)
    private int autoPayCount;                        // 자동결제 횟수


    private LocalDateTime paymentNextTime;                      // 자동결제 마지막결제일

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
//    private User user;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id",nullable = false)
//    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id",nullable = false)
    private Payment payment;






}
