package com.os.repository;

import com.os.dto.SaveProductDTO;
import com.os.entity.SaveProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaveProductRepository extends JpaRepository<SaveProduct,Long> {

    List<SaveProduct> findBySavePaymentId(Long s_paymentId);
}
