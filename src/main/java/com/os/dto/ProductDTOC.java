package com.os.dto;

import com.os.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTOC {
    private Long id;                            // product IDX
    private String productName;                 // 상품 명
    private int productTotalItems;              // 상품 수량
    private int productPrice;                   // 상품 가격
    private int productAmount;                  // 상품 총액

    public static ProductDTOC productInfoDTO(Product product){
        ProductDTOC productDTOC = ProductDTOC.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productTotalItems(product.getProductTotalItems())
                .productAmount(product.getProductAmount())
                .build();

        return productDTOC;
    }
}
