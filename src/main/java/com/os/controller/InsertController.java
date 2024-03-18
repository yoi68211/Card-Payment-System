package com.os.controller;

import com.os.dto.InsertDTO;
import com.os.service.PaymentService;
import com.os.util.OrderType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class InsertController {

    private final PaymentService paymentService;

    public InsertController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/insert")
    public void insertData(@RequestBody InsertDTO dto) {

        System.out.println(dto.toString()); 
        paymentService.insert_basic(dto);


        System.out.println("결제 등록 성공");
    }
}
