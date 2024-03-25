package com.os.repository;

import com.os.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    // CustomerId와 delYn이 각각 주어진 값과 일치하는 Payment 엔티티를 찾습니다.
    Optional<Payment> findByCustomerIdAndPaymentDelYn(Long customerId, char delYn);

}
