package com.os.repository;

import com.os.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllPaymentListRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByPaymentDelYnNot(char paymentDelYn, Pageable pageable);

    Page<Payment> findByPaymentTitleContainingAndPaymentDelYnNot(String keyword, char paymentDelYn, Pageable pageable);
}