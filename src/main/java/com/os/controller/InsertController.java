package com.os.controller;

import com.os.dto.InsertDTO;
import com.os.service.PaymentService;
import com.os.util.OrderType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String insertData(@RequestBody @Valid InsertDTO dto) {


           boolean res = paymentService.insert_basic(dto);

           if (res){

               return "ok";

           }else {
               return "bad";
           }



    }
}
