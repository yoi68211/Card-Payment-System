package com.os.dto;

import com.os.entity.Product;
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
        ProductDTO productDTO = ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productTotalItems(product.getProductTotalItems())
                .productAmount(product.getProductAmount())
                .build();

        return productDTO;
    }
}
