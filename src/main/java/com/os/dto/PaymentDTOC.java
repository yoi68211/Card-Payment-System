package com.os.dto;

import com.os.entity.Payment;
import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PaymentDTOC {

    private Long id;                         // 결제 IDX
    private String paymentTitle;                    // 결제 제목
    private OrderType paymentType;                  // 결제 종류(자동결제 / 일반결제)
    private BizTo paymentBizTo;                     // 결제 구분(BtoC / BtoB)
    private String paymentMemo;                     // 결제 메모
    private LocalDateTime paymentCreateTime;                      // 결제 생성시간
    private OrderStatus paymentStatus;                     // 결제상태(성공 / 불가 / 오류)
    private LocalDateTime paymentUpdateTime;               // 수정시간
    private char paymentDelYn;                             // 결제 삭제여부(Y / N)
    private int paymentMonth;                           // 자동결제 다음결제일
    private LocalDateTime paymentNextTime;                            // 자동결제 마지막결제일
    private int paymentFirstPay;

    public static PaymentDTOC payInfoDTO(Payment payment){
        PaymentDTOC paymentDTOC = PaymentDTOC.builder()
                .id(payment.getId())
                .paymentTitle(payment.getPaymentTitle())
                .paymentType(payment.getPaymentType())
                .paymentBizTo(payment.getPaymentBizTo())
                .paymentMemo(payment.getPaymentMemo())
                .paymentCreateTime(payment.getPaymentCreateTime())
                .paymentStatus(payment.getPaymentStatus())
                .paymentUpdateTime(payment.getPaymentUpdateTime())
                .paymentDelYn(payment.getPaymentDelYn())
                .paymentMonth(payment.getPaymentMonth())
                .paymentNextTime(payment.getPaymentNextTime())
                .paymentFirstPay(payment.getPaymentFirstPay())
                .build();

        return  paymentDTOC;
    }

}
