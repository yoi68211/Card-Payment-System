package com.os.aipTests;

import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.dto.PaymentDetailsDTO;
import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentDetailsDTOTest {

    @Test
    public void testPaymentDetailsDTOCreation() {
        // 가짜 Customer 객체 생성
        Customer customer = Customer.builder()
                .customerName("John Doe")
                .customerEmail("john@example.com")
                .customerAddress("123 Main St, Anytown")
                .build();

        // 가짜 Product 객체 생성
        Product product1 = Product.builder()
                .productName("Product 1")
                .productTotalItems(1)
                .productPrice(100)
                .productAmount(2000)
                .build();

        Product product2 = Product.builder()
                .productName("Product 2")
                .productTotalItems(2)
                .productPrice(50)
                .productAmount(1000)
                .build();

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        // 가짜 Payment 객체 생성
        Payment payment = Payment.builder()
                .paymentTitle("Payment")
                .paymentType(OrderType.auto)
                .paymentBizTo(BizTo.BtoC)
                .paymentMemo("Memo")
                .paymentCreateTime(LocalDateTime.now())
                .paymentStatus(OrderStatus.paid)
                .paymentDelYn('N')
                .paymentMonth(1)
                .paymentNextTime(LocalDateTime.now().plusMonths(1))
                .paymentFirstPay(50)
                .products(products)
                .build();

//        List<Payment> payments = new ArrayList<>();
//        payments.add(payment);

//        customer.setPayments(payments);

        // PaymentDetailsDTO 생성
        PaymentDetailsDTO dto = PaymentDetailsDTO.builder()
                .customer(customer)
                .build();

        // 생성된 PaymentDetailsDTO 검증
        assertNotNull(dto);
        assertEquals("John Doe", dto.getCustomerName());
        assertEquals("john@example.com", dto.getCustomerEmail());
//        assertEquals(payments, dto.getPayments());
        assertNotNull(dto.getCreateTime());
        assertEquals("3000", dto.getAmount()); // product1의 총액(100) + product2의 총액(100) = 200
    }
}
