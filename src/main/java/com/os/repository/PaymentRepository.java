package com.os.repository;

import com.os.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {



    Optional<Payment> findByDocumentNo(String documentNo);

    @Query("SELECT COUNT(p) Fent p WHERE p.status = 'wait' AND p.type = 'auto' AND MONTH(p.createTime) = MONTH(:currentDate)")
    long countWaitAutoPayments(@Param("currentDate") LocalDateTime currentDate);

    //    ROM Payment p WHERE p.status = 'error' AND p.type = 'auto' AND MONTH(p.createTime) = MONTH(:currentDate)")

    long countErrorAutoPayments(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status = 'paid' AND p.type = 'auto' AND MONTH(p.createTime) = MONTH(:currentDate)")
    long countSuccessAutoPayments(@Param("currentDate") LocalDateTime currentDate);

   // @Query("SELECT COUNT(p) FROM Paym
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status <> 'stop' AND p.type = 'auto' AND MONTH(p.createTime) = MONTH(:currentDate)")
    long countAutoPayments(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status <> 'stop' AND MONTH(p.createTime) = MONTH(:currentDate)")
    long countPayments(@Param("currentDate") LocalDateTime currentDate);

    //@Query("SELECT * FROM Payment p WHERE p.status = 'wait' AND p.type = 'auto' AND MONTH(p.createTime) = MONTH(:currentDate)")
    //Optional<Payment> findWaitAutoPayments(@Param("currentDate") LocalDateTime currentDate);
}
