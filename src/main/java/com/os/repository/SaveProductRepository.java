package com.os.repository;

import com.os.entity.SaveProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveProductRepository extends JpaRepository<SaveProduct,Long> {
}
