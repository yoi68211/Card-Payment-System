package com.os.repository;

import com.os.entity.SavePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavePaymentRepository extends JpaRepository<SavePayment,Long> {
    long countByUserId(Long userId);

    Optional<SavePayment> findByUserId(long userId);

}
