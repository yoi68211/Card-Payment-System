package com.os.dto;

import com.os.entity.Payment;

import com.os.util.OrderStatus;
import com.os.util.OrderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllPaymentListDto {
    private LocalDateTime createTime;
    private String documentNo;
    private String name;
    private String title;
    private String amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static AllPaymentListDto toAllPaymentListDto(Payment payment) {
        return AllPaymentListDto.builder()
                .createTime(payment.getCreateTime())
                .documentNo(payment.getDocumentNo())
                .name(payment.getCustomers().get(0).getName())
                .title(payment.getTitle())
                .amount(payment.getProducts().get(0).getAmount())
                .status(payment.getStatus())
                .build();
    }
    /*
    private Long paymentId;
    private LocalDateTime createTime;
    private String documentNo;
    private String memo;
    private LocalDateTime paymentCycle;
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String title;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    private LocalDateTime updateTime;

    public static AllPaymentListDto toAllPaymentListDto(Payment payment) {
        return AllPaymentListDto.builder()
                .paymentId(payment.getPaymentId())
                .createTime(payment.getCreateTime())
                .documentNo(payment.getDocumentNo())
                .memo(payment.getMemo())
                .paymentCycle(payment.getPaymentCycle())
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus())
                .title(payment.getTitle())
                .type(payment.getType())
                .updateTime(payment.getUpdateTime())
                .build();
    }
     */
}
