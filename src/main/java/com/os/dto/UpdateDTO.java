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

    private Long customerId; // 고객 id
    private String customerName; // 고객이름
    private String customerEmail; // 고객 이메일

    private Long paymentId;  // 수정할 paymentId
    private String paymentTitle; // 제목
    private List<Long> productDelCheck; // 삭제할 productIdl
    private List<ProductDTO> productList; // 추가되거나 업데이트할 리스트

}
