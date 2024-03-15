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
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavePayment {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long s_paymentId;                       // 결제임시저장 IDX

    private String s_paymentName;                    // 결제임시저장 고객명

    private String s_paymentEail;                   // 결제임시저장 이메일

    private String s_paymentPhone;                   // 결제임시저장 연락처

    private String s_paymentAddress;                 // 결제임시저장 주소

    private String s_paymentTitle;                   // 결제임시저장 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType s_paymentType;                 // 결제임시저장 결제종류(자동결제 / 일반결제)

    @Column(nullable = false)
    private String s_paymentFirstpay;                // 결제임시저장 처음결제금액

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BizTo s_paymentBizTo;                    // 결제임시저장 결제구분(BtoC / BtoB)

    private String s_paymentCycle;                   // 결제임시저장 다음결제일(다음달 / 다다음달)

    private String s_paymentDate;                   // 결제임시저장 마지막결제일(1 ~ 31)

    private String s_paymentPay;                     // 결제임시저장 결제금액


    // 유저 IDX

    ///////////////////////////////////////////////////////////////////////////

    /*@CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;  */     // 생성시간




//    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
//    private List<SaveProduct> saveProducts;


    // TODO: 2024-03-14 아래르 SaveProduct 안에 추가 하셔야합니다 -김홍성-
    //  @ManyToOne
    //  @JoinColumn(name = "paymentId")
    //  private SavePayment savePayment;



}
