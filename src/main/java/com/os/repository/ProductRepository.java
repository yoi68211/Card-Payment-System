package com.os.repository;

import com.os.entity.Payment;
import com.os.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByPayment(Payment payment);
}
