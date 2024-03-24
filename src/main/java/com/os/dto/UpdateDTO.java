package com.os.dto;

import com.os.util.BizTo;
import com.os.util.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
public class UpdateDTO {

    private Long customerId;
    private String customerName;
    private String customerEmail;

    private Long paymentId;
    private String paymentTitle;

    private List<Long> productId;
    private List<ProductDTO> productList;

}
