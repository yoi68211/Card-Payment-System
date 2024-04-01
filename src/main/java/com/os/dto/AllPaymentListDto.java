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
    private Long id;
    private String customerName;
    private String paymentTitle;
    private String totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus paymentStatus;
    private char paymentDelYn;

    public static AllPaymentListDto toAllPaymentListDto(Payment payment) {
        String totalAmount = payment.calculateTotalAmount(payment.getProducts());

        return AllPaymentListDto.builder()
                .createTime(payment.getCreateTime())
                .id(payment.getCustomer().getId())
                .customerName(payment.getCustomer().getCustomerName())
                .paymentTitle(payment.getPaymentTitle())
                .totalAmount(totalAmount)
                .paymentStatus(payment.getPaymentStatus())
                .paymentDelYn(payment.getPaymentDelYn())
                .build();
    }
}
