package com.os.dto;

import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.util.BizTo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class PaymentDetailsDTO {

    private Long id;
    private String customerName;
    private String customerEmail;
    private LocalDateTime createTime;
    private Payment payments;
    private String paymentTitle;
    private String amount;
    private BizTo bizTo;


    @Builder
    public PaymentDetailsDTO(Customer customer) {

        this.id = customer.getPayments().getId();

        this.customerName = customer.getCustomerName();
        this.customerEmail = customer.getCustomerEmail();
        this.payments = customer.getPayments();
        this.createTime = customer.getPayments().getPaymentCreateTime();

        this.paymentTitle = customer.getPayments().getPaymentTitle();

        this.bizTo = customer.getPayments().getPaymentBizTo();



        this.amount = customer.getPayments().calculateTotalAmount(payments.getProducts());
    }




}
