package com.os.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "customer_id")
    private Long id;            // 고객 IDX

    @Column(nullable = false)
    private String customerName;               // 고객 이름

    @Column(nullable = false)
    private String customerEmail;              // 고객 이메일

    @Column(nullable = false)
    private String customerPhone;              // 고객 전화번호

    @Column(nullable = false)
    private String customerAddress;            // 고객 주소

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;                        // 유저 IDX


    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;


//    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<AutoPayment> autoPayments;


}
