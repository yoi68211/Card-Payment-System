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


    @Column(nullable = false)
    private long userId;                   // user id값


    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;

}
