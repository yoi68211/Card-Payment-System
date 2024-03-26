package com.os.repository;

import com.os.entity.Customer;
import com.os.util.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    List<Customer> findByPayments_PaymentStatusAndPayments_CreateTimeBetween(OrderStatus orderStatus, LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    long countByPayments_PaymentStatusAndPayments_CreateTimeBetween(OrderStatus orderStatus, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
