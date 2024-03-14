package com.os.entity;

import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;                 // payment 순번

    @Column(nullable = false)
    private String documentNo;              // 문서번호

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

    @Column(nullable = false)
    private String createTime;              // 작성일*/

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

    @OneToOne
    @JoinColumn(name = "autoPaymentId")
    private AutoPayment autoPayment;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;




}
