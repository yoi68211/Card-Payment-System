package com.os.dto;

import com.os.entity.Payment;
import com.os.util.AutoStatus;
import com.os.util.BizTo;
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
public class AutoPaymentListDto {
    @Enumerated(EnumType.STRING)
    private AutoStatus autoStatus;
    private String customerName;
    private String customerEmail;
    @Enumerated(EnumType.STRING)
    private BizTo paymentBizTo;
    private String totalAmount;
    private LocalDateTime paymentNextTime;
    private LocalDateTime updateTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus paymentStatus;
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderType paymentType;
    private String customerPhone;

    public static AutoPaymentListDto toAutoPaymentListDto(Payment payment) {
        String totalAmount = payment.calculateTotalAmount(payment.getProducts());

        return AutoPaymentListDto.builder()
                .id(payment.getId())
                .paymentBizTo(payment.getPaymentBizTo())
                .autoStatus(payment.getAutoPayments().getAutoStatus())
                .customerName(payment.getCustomer().getCustomerName())
                .customerEmail(payment.getCustomer().getCustomerEmail())
                .totalAmount(totalAmount)
                .paymentNextTime(payment.getAutoPayments().getPaymentNextTime())
                .updateTime(payment.getAutoPayments().getUpdateTime())
                .paymentStatus(payment.getPaymentStatus())
                .id(payment.getAutoPayments().getId())
                .paymentType(payment.getPaymentType())
                .customerPhone(payment.getCustomer().getCustomerPhone())
                .build();
    }
}
