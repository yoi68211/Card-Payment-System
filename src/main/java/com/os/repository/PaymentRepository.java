package com.os.repository;

import com.os.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    // CustomerId와 delYn이 각각 주어진 값과 일치하는 Payment 엔티티를 찾습니다.
    Optional<Payment> findByCustomerIdAndPaymentDelYn(Long customerId, char delYn);

    @Query("SELECT DATE(p.createTime), COUNT(p) FROM Payment p WHERE MONTH(p.createTime) = :month GROUP BY DATE(p.createTime) ORDER BY DATE(p.createTime)")
    List<Object[]> countByDateInMonth(@Param("month") int month);

}
