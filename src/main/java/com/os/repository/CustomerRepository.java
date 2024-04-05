package com.os.repository;

import com.os.entity.Customer;
import com.os.util.OrderStatus;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long>  {

    //결제 성공목록
    List<Customer> findByPayments_PaymentStatus(OrderStatus orderStatus);

    //이번달 결제등록 목록
    List<Customer> findByAndPayments_CreateTimeBetween(LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    long countByPayments_PaymentDelYnAndCreateTimeBetween(char delYn,LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    long countByPayments_PaymentStatusAndPayments_PaymentDelYnAndUpdateTimeBetween(OrderStatus orderStatus,char delYn,LocalDateTime startDate, LocalDateTime endDate);

    List<Customer> findByPayments_PaymentDelYn(Character yn);

}
