package com.os.controller;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class PayInfoController {


    @GetMapping("/payInfoDetail/{id}")
    public String payInfoDetail(@PathVariable long id, Model model) {
        System.out.println("받은 id =>" + id);


        return "paylist/basicPayInfoDetail";

        // 결제 타입이 auto 면 자동결제상세 페이지
        // 아니면 전체결제 상세페이지
    }


}
