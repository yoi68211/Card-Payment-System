package com.os.repository;

import com.os.entity.AutoPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoPaymentRepository extends JpaRepository<AutoPayment,Long> {
}
