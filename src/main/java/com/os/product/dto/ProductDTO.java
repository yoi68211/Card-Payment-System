package com.os.product.dto;

import com.os.product.entity.Product;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO {
    private Long id;
    private String productName;
    private int productTotalItems;
    private int productPrice;
    private int productAmount;

    public static ProductDTO productInfoDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productTotalItems(product.getProductTotalItems())
                .productAmount(product.getProductAmount())
                .build();

    }
}
