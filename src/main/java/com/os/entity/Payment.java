package com.os.entity;

import com.os.dto.InsertDTO;
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
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @Column(nullable = false, name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                         // 결제 IDX


    @Column(nullable = false)
    private String paymentTitle;                    // 결제 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType paymentType;                  // 결제 종류(자동결제 / 일반결제)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BizTo paymentBizTo;                     // 결제 구분(BtoC / BtoB)

    private String paymentMemo;                     // 결제 메모

   /* @CreationTimestamp*/
    @Column(nullable = false)
    private LocalDateTime paymentCreateTime;                      // 결제 생성시간

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus paymentStatus;                     // 결제상태(성공 / 불가 / 오류)


    @UpdateTimestamp
    private LocalDateTime paymentUpdateTime;               // 수정시간

    @Column(nullable = false)
    private char paymentDelYn;                             // 결제 삭제여부(Y / N)



    private int paymentMonth;                           // 자동결제 다음결제일




    private LocalDateTime paymentNextTime;                            // 자동결제 마지막결제일


    private int paymentFirstPay;                                 // 자동결제 금액


    //////////////////////////////////////////////////////////////////////////////////
    // 고객 IDX
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id",nullable = false)
    private Customer customer;


    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;


    public LocalDateTime calculateLocalDateTime(InsertDTO dto){
        return paymentNextTime.plusMonths(dto.getPaymentMonth()).withDayOfMonth(dto.getAutoDate());

    }



}
