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
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String address;

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
