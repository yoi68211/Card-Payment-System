package com.os.customer.dto;

import com.os.customer.entity.Customer;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerLoadDTO {
    private Long id;            // 고객 IDX
    private String customerName;               // 고객 이름
    private String customerEmail;              // 고객 이메일
    private String customerPhone;              // 고객 전화번호
    private String customerAddress;

    public static CustomerLoadDTO CustomerInfoDTO(Customer customer){
        CustomerLoadDTO customerDTOC = CustomerLoadDTO.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .customerEmail(customer.getCustomerEmail())
                .customerPhone(customer.getCustomerPhone())
                .customerAddress(customer.getCustomerAddress())
                .build();

        return  customerDTOC;
    }
}
