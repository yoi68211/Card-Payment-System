package com.os.dto;

import com.os.entity.SavePayment;
import com.os.util.BizTo;
import com.os.util.OrderType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SavePaymentDTO {

//    private String documentNo;
//    private LocalDateTime paymentCreateTime;
    private String s_paymentName;
    private String s_paymentEmail;
    private String s_paymentPhone;
    private String s_paymentAddress;
    private String s_paymentTitle;
    private OrderType s_paymentType;

    private int s_paymentFirstPay;

    private BizTo s_paymentBizTo;
    private int s_paymentCycle;
    private int s_paymentDate;
    private int s_paymentPay;
    private List<SaveProductDTO> productList;



}
