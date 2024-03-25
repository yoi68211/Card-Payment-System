package com.os.service;
import com.os.entity.Customer;
import com.os.repository.CustomerRepository;
import com.os.util.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
@SpringBootTest
class CustomerServiceTest {
    private final CustomerRepository customerRepository;

    @Autowired
    CustomerServiceTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Test
    public void testThisMonthPaidSearch() {
        LocalDateTime now = LocalDateTime.now();
        YearMonth thisMonth = YearMonth.from(now);
        LocalDateTime startOfMonth = thisMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = thisMonth.atEndOfMonth().atTime(23, 59, 59);

        // 데이터베이스에서 실제 데이터 조회
        List<Customer> customers = customerRepository.findByPayments_PaymentStatusAndPayments_CreateTimeBetween(OrderStatus.wait, startOfMonth, endOfMonth);

        for (Customer customer : customers) {
            System.out.println("customer = " + customer.getId());
            System.out.println("customer = " + customer.getCustomerName());
        }
        long count = customerRepository.countByPayments_PaymentStatusAndPayments_CreateTimeBetween(OrderStatus.wait, startOfMonth, endOfMonth);
        System.out.println("Count: " + count);




    }

}