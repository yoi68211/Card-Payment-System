package com.os.repository;

import com.os.dto.SavePaymentLoadDTO;
import com.os.entity.SavePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavePaymentRepository extends JpaRepository<SavePayment,Long> {
    Optional<SavePayment> findByUserId(long userId);
}
