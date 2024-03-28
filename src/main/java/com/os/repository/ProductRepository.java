package com.os.repository;

import com.os.entity.Payment;
import com.os.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByPaymentId(Long paymentId);
}
