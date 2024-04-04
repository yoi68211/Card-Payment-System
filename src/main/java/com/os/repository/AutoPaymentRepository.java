package com.os.repository;

import com.os.entity.AutoPayment;
import com.os.util.AutoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
public interface AutoPaymentRepository extends JpaRepository<AutoPayment,Long> {
    long countByAutoStatusAndUpdateTimeBetween(AutoStatus autoStatus, LocalDateTime startDate, LocalDateTime endDate);

}
