package com.os.toss;

import lombok.Data;

@Data
public class PaymentRequest {
    private String paymentKey;
    private String orderId;
    private String amount;

    public boolean isValid() {
        // 유효성 검사 논리를 구현하여 필요한 경우 확인합니다.
        return paymentKey != null && orderId != null && amount != null;

    }
}