package com.os.entity;

import com.os.dto.InsertDTO;
import com.os.util.AutoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;                              // 자동결제 IDX

/*
    @Column(nullable = false)
    private String autoFirstpay;                        // 자동결제 첫결제금액
*/

    @Column(nullable = false)
    private Long autoMonth;                           // 자동결제 다음결제일

    @Column(nullable = false)
    private LocalDateTime autoCreateTime;                            // 자동결제 마지막결제일

    @Column(nullable = false)
    private int autoPay;                             // 자동결제 금액

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AutoStatus autoStatus;                          // 자동결제 상태

    @Column(nullable = false)
    private int autoPayCount;                        // 자동결제 횟수


    @OneToOne(mappedBy = "autoPayment", cascade = CascadeType.ALL)
    private Payment payment;


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public LocalDateTime calculateLocalDateTime(InsertDTO dto){
        return autoCreateTime.plusMonths(dto.getAutoMonth()).withDayOfMonth(dto.getAutoDate());

    }




}
