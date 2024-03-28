package com.os.repository;

import com.os.dto.SavePaymentLoadDTO;
import com.os.entity.SavePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public interface SavePaymentRepository extends JpaRepository<SavePayment,Long> {
    Optional<SavePayment> findByUserId(long userId);
}
