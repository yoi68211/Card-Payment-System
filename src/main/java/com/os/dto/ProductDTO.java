package com.os.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private int productTotalItems;
    private int productPrice;
    private int productAmount;
}
