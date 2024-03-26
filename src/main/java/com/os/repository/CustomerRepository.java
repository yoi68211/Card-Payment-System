package com.os.repository;

import com.os.entity.Customer;
import com.os.util.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //결제 성공목록
    List<Customer> findByPayments_PaymentStatus(OrderStatus orderStatus);



    //이번달 결제등록 목록
    List<Customer> findByAndPayments_CreateTimeBetween(LocalDateTime startOfMonth, LocalDateTime endOfMonth);


    long countByPayments_CreateTimeBetween(LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    long countByPayments_PaymentStatus(OrderStatus orderStatus);
}
