package com.os.repository;

import com.os.dto.SaveProductDTO;
import com.os.entity.SaveProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface SaveProductRepository extends JpaRepository<SaveProduct,Long> {

    List<SaveProduct> findBySavePaymentId(Long s_paymentId);
}
