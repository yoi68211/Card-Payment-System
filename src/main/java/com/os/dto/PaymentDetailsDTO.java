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
    private List<Payment> payments;
    private String paymentTitle;
    private String amount;
    private BizTo bizTo;


    @Builder
    public PaymentDetailsDTO(Customer customer) {

        this.id = customer.getId();
        this.customerName = customer.getCustomerName();
        this.customerEmail = customer.getCustomerEmail();
        this.payments = customer.getPayments();
        this.createTime = customer.getPayments().stream()
                .map(Payment::getPaymentCreateTime)
                .min(LocalDateTime::compareTo)
                .orElse(null);

        this.paymentTitle = customer.getPayments().stream()
                .map(Payment::getPaymentTitle)
                .findFirst()
                .orElse(null);

        this.bizTo = customer.getPayments().stream()
                .map(Payment::getPaymentBizTo)
                .findFirst()
                .orElse(null);


        this.amount = calculateTotalAmount(customer.getPayments());
    }

    private String calculateTotalAmount(List<Payment> payments) {
        int totalAmount = payments.stream()
                .flatMap(payment -> payment.getProducts().stream()) // 모든 Product를 하나의 스트림으로 평면화
                .mapToInt(Product::getProductAmount) // Product의 총액을 가져옴
                .sum();
        return String.valueOf(totalAmount);
    }


}
