package com.os.dto;

import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.util.BizTo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDetailsDTO {
    private Long id;
    private String orderName;
    private String customerName;
    private String customerEmail;
    private String customerMobilePhone;
    private LocalDateTime updateTime;
    private String customerKey;
    private String amount;

    private BizTo bizTo;

    @Builder
    public PaymentDetailsDTO(Customer customer) {
        this.id = customer.getPayments().getId();
        this.customerName = customer.getCustomerName();
        this.customerEmail = customer.getCustomerEmail();

        Payment payment = customer.getPayments();
        this.updateTime = customer.getUpdateTime();
        this.orderName = customer.getPayments().getPaymentTitle();
        this.bizTo = customer.getPayments().getPaymentBizTo();
        this.customerMobilePhone = customer.getCustomerPhone();
        this.amount = customer.getPayments().calculateTotalAmount(payment.getProducts());
        this.customerKey = String.valueOf(customer.getCustomerKey());

    }
}
