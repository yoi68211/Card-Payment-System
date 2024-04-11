package com.os.repository;

import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.util.AutoOrderStatus;
import com.os.util.AutoStatus;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public interface AutoPaymentRepository extends JpaRepository<AutoPayment,Long> {
    long countByAutoStatusAndUpdateTimeBetween(AutoStatus autoStatus, LocalDateTime startDate, LocalDateTime endDate);
    long countByUpdateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    Optional<AutoPayment> findByPaymentId(Long id);
    Page<AutoPayment> findAll(Pageable pageable);
    Page<AutoPayment> findByPayment_Customer_CustomerNameAndAutoOrderStatus(String keyword, AutoOrderStatus autoOrderStatus, Pageable pageable);

    Page<AutoPayment> findByAutoStatus(AutoStatus autoStatus, Pageable pageable);
}
