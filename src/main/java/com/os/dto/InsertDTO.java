package com.os.dto;

import com.os.util.BizTo;
import com.os.util.OrderType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InsertDTO {

    private String documentNo;
    private String createTime;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String title;
    private OrderType type;
    private String firstPay;
    private BizTo bizTo;
    private String cycle;
    private String paymentDate;
    private String pay;
    private String memo;
    private List<ProductDTO> productList;
}
