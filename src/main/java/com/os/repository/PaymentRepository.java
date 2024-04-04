package com.os.repository;

import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.util.AutoOrderStatus;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Transactional
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    // CustomerId와 delYn이 각각 주어진 값과 일치하는 Payment 엔티티를 찾습니다.
    Optional<Payment> findByCustomerIdAndPaymentDelYn(Long customerId, char paymentDelYn);


    Page<Payment> findByPaymentDelYn(char n, Pageable pageable);

    Page<Payment> findByPaymentTitleContainingAndPaymentDelYnNot(String keyword, char y, Pageable pageable);

    @Query("SELECT COUNT(p) FROM Payment p WHERE DATE(p.createTime) = :date")
    Long countPaymentsByDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(p) FROM Payment p WHERE DATE(p.createTime) >= :startDate AND DATE(p.createTime) <= :endDate")
    Long countPaymentsYearByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query("SELECT p FROM Payment p JOIN FETCH p.autoPayments")
    List<Payment> findAllPaymentsWithAutoPayments();

    ///////////////////////////////////
    long countByPaymentStatusAndPaymentTypeAndUpdateTimeBetween(OrderStatus paymentStatus, OrderType paymentType, LocalDateTime startTime, LocalDateTime endTime);

    ///////////////////////////////////
    long countByPaymentTypeAndUpdateTimeBetween(OrderType paymentType, LocalDateTime startTime, LocalDateTime endTime);
}
