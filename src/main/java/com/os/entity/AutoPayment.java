package com.os.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long autoId;                        // 자동결제 IDX

    @Column(nullable = false)
    private Long autoFirstpay;                        // 자동결제 첫결제금액

    @Column(nullable = false)
    private Long autoCycle;                        // 자동결제 다음결제일

    @Column(nullable = false)
    private Long autoDate;                        // 자동결제 마지막결제일

    @Column(nullable = false)
    private Long autoPay;                        // 자동결제 금액

    @Column(nullable = false)
    private Long autoStatus;                        // 자동결제 상태

    @Column(nullable = false)
    private Long autoPayCount;                        // 자동결제 횟수

    // 결제 IDX

    ///////////////////////////////////////////////////////////////////

}
