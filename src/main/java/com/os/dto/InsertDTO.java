package com.os.dto;

import lombok.Data;

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
    private String type;
    private String firstPay;
    private String bizTo;
    private String cycle;
    private int paymentDate;
    private int pay;
    private String memo;
    private List<ProductDTO> productList;
}
