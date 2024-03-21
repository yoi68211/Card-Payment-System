package com.os.service;

import com.os.dto.PaymentDTOC;
import com.os.dto.ProductDTOC;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.repository.PaymentRepository;
import com.os.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ProductServiceC {

    private final ProductRepository productRepository;

    public List<ProductDTOC> productRoad(Long id){
        List<Product> productList = productRepository.findByPaymentId(id);

        List<ProductDTOC> productInfoList = new ArrayList<>(); // productInfoList 변수 선언 및 초기화



        if(!productList.isEmpty()){
            for(Product product : productList){
                ProductDTOC productInfo = ProductDTOC.productInfoDTO(product);
                productInfoList.add(productInfo);
            }
            return productInfoList;
        } else{
            return new ArrayList<>(); // 빈 리스트 반환
        }
    }
}
