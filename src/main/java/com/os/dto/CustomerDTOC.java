package com.os.dto;

import com.os.entity.Customer;
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
public class CustomerDTOC {
    private Long id;            // 고객 IDX
    private String customerName;               // 고객 이름
    private String customerEmail;              // 고객 이메일
    private String customerPhone;              // 고객 전화번호
    private String customerAddress;

    public static CustomerDTOC CustomerInfoDTO(Customer customer){
        CustomerDTOC customerDTOC = CustomerDTOC.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .customerEmail(customer.getCustomerEmail())
                .customerPhone(customer.getCustomerPhone())
                .customerAddress(customer.getCustomerAddress())
                .build();

        return  customerDTOC;
    }
}
