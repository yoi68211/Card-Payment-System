package com.os.repository;

import com.os.customer.entity.Customer;
import com.os.util.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long>  {

    long countByPayments_PaymentDelYnAndCreateTimeBetween(char delYn,LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    long countByPayments_PaymentStatusAndPayments_PaymentDelYnAndUpdateTimeBetween(OrderStatus orderStatus,char delYn,LocalDateTime startDate, LocalDateTime endDate);

    List<Customer> findByPayments_PaymentDelYn(Character yn);

}
