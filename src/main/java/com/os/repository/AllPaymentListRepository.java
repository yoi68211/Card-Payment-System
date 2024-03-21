package com.os.repository;

import com.os.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllPaymentListRepository extends JpaRepository<Payment, Long> {
}