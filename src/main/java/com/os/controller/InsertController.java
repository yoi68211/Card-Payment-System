package com.os.controller;

import com.os.dto.InsertDTO;
import com.os.service.CustomerService;
import com.os.service.PaymentService;
import com.os.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InsertController {

    private final PaymentService paymentService;


    public InsertController(ProductService productService, CustomerService customerService, PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/insert")
    public void insertData(@RequestBody InsertDTO dto) {

        System.out.println(dto.toString());

    }
}
