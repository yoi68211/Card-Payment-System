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
    private LocalDateTime paymentCreateTime;
    private Long id;
    private String customerName;
    private String paymentTitle;
    private int productAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus paymentStatus;

    public static AllPaymentListDto toAllPaymentListDto(Payment payment) {
        return AllPaymentListDto.builder()
                .paymentCreateTime(payment.getCreateTime())
                .id(payment.getId())
                .customerName(payment.getCustomer().getCustomerName())
                .paymentTitle(payment.getPaymentTitle())
                .productAmount(payment.getProducts().get(0).getProductAmount())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
