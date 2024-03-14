package com.os.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long customerId;            // customer 순번

    @Column(nullable = false)
    private String name;                // 고객명

    @Column(nullable = false)
    private String email;               // 이메일

    @Column(nullable = false)
    private String phone;               // 전화번호

    @Column(nullable = false)
    private String address;             // 주소

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;


//    @Builder
//    public Customer(Long customerId, String name, String email, String phone, String address, Payment payment) {
//        this.customerId = customerId;
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.payment = payment;
//    }
}
