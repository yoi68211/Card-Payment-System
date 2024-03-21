package com.os.entity;

import com.os.dto.SavePaymentDTO;
import com.os.util.BizTo;
import com.os.util.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;                       // 결제임시저장 IDX

    private String s_paymentName;                    // 결제임시저장 고객명

    private String s_paymentEmail;                   // 결제임시저장 이메일

    private String s_paymentPhone;                   // 결제임시저장 연락처

    private String s_paymentAddress;                 // 결제임시저장 주소

    private String s_paymentTitle;                   // 결제임시저장 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType s_paymentType;                 // 결제임시저장 결제종류(자동결제 / 일반결제)

    @Column(nullable = false)
    private int s_paymentFirstpay;                // 결제임시저장 처음결제금액

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private BizTo s_paymentBizTo;                    // 결제임시저장 결제구분(BtoC / BtoB)

    private int s_paymentCycle;                   // 결제임시저장 다음결제일(다음달 / 다다음달)

    private int s_paymentDate;                   // 결제임시저장 마지막결제일(1 ~ 31)

    private int s_paymentPay;                     // 결제임시저장 결제금액



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" , referencedColumnName = "id",nullable = false)
    private User user;


    @OneToMany(mappedBy = "savePayment", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<SaveProduct> saveProducts;

    public void toSavePayment(SavePaymentDTO dto){
        SavePayment savepayment = new SavePayment();
        savepayment.setS_paymentName(dto.getS_paymentName());
        savepayment.setS_paymentEmail(dto.getS_paymentEmail());
        savepayment.setS_paymentPhone(dto.getS_paymentPhone());
        savepayment.setS_paymentAddress(dto.getS_paymentAddress());
        savepayment.setS_paymentTitle(dto.getS_paymentTitle());
        savepayment.setS_paymentType(dto.getS_paymentType());
        savepayment.setS_paymentFirstpay(dto.getS_paymentFirstPay());
        savepayment.setS_paymentBizTo(dto.getS_paymentBizTo());
        savepayment.setS_paymentCycle(dto.getS_paymentCycle());
        savepayment.setS_paymentDate(dto.getS_paymentDate());
        savepayment.setS_paymentPay(dto.getS_paymentPay());
        savepayment.setUser(user);



    }

}
