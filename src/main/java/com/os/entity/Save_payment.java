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
public class Save_payment {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long s_paymentId;                 // 임시저장ID

    @Column(nullable = false)
    private String documentNo;              // 문서번호

    @Column(nullable = false)
    private String createTime;              // 작성일

    @Column(nullable = false)
    private String name;                    // 고객명

    @Column(nullable = false)
    private String email;                   // 이메일

    @Column(nullable = false)
    private String phone;                   // 연락처

    @Column(nullable = false)
    private String address;                 // 주소

    @Column(nullable = false)
    private String title;                   // 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;                 // 결제종류(자동결제 / 일반결제)

    @Column(nullable = false)
    private String firstPay;                // 처음결제금액 동일여부(같다 / 다르다)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BizTo bizTo;                    // 결제구분(BtoC / BtoB)

    private String cycle;                   // 다음결제일(다음달 / 다다음달)

    private String paymentDate;             // 결제일(1 ~ 31)

    private String pay;                     // 결제금액

    private String memo;                    // 결제등록메모



    /*@CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;  */     // 생성시간

    @UpdateTimestamp
    private LocalDateTime updateTime;       // 수정시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;             // 결제상태(성공 / 불가 / 오류)

    @Column(nullable = false)
    private char delYn;                     // 삭제여부(Y / N)

    @Column(nullable = false)
    private long userId;                   // user id값

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Product> products;

    @OneToMany(mappedBy = "payment",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers;

    /////////////////////////////////////////////
    // 임시저장 테이블 관계지정



    /////////////////////////////////////////////

//    @Builder
//    public Payment(Long paymentId, String documentNo, String title, BizTo bizTo, String memo, OrderType type, OrderStatus status, LocalDateTime paymentDate, LocalDateTime paymentCycle, LocalDateTime createTime, LocalDateTime updateTime, List<Product> products, List<Customer> customers) {
//        this.paymentId = paymentId;
//        this.documentNo = documentNo;
//        this.title = title;
//        this.bizTo = bizTo;
//        this.memo = memo;
//        this.type = type;
//        this.status = status;
//        this.paymentDate = paymentDate;
//        this.paymentCycle = paymentCycle;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//        this.products = products;
//        this.customers = customers;
//    }
}
