package com.os.repository;

import com.os.entity.Customer;
import com.os.entity.Payment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AllPaymentListRepository extends JpaRepository<Payment, Long> {


    Page<Payment> findByPaymentDelYn(char paymentDelYn, Pageable pageable);

    Page<Payment> findByPaymentTitleContainingAndPaymentDelYnNot(String keyword, char paymentDelYn, Pageable pageable);


}