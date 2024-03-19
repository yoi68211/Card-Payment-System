package com.os.controller;

import com.os.repository.PaymentRepository;
import com.os.service.DashBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
@Controller
public class DashBoardController {

    private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GetMapping("/dashboard")
    public String Mapping(Model model) {
        LocalDateTime currentDate = LocalDateTime.now();
        long errorAutoPaymentCount = dashBoardService.countErrorAutoPayments(currentDate);
        long successAutoPaymentCount = dashBoardService.countSuccessAutoPayments(currentDate);
        long waitAutoPaymentCount = dashBoardService.countWaitAutoPayments(currentDate);
        long autoPaymentCount = dashBoardService.countAutoPayments(currentDate);
        long paymentCount = dashBoardService.countPayments(currentDate);

        model.addAttribute("errorAutoPaymentCount", errorAutoPaymentCount);
        model.addAttribute("successAutoPaymentCount", successAutoPaymentCount);
        model.addAttribute("waitAutoPaymentCount", waitAutoPaymentCount);
        model.addAttribute("autoPaymentCount", autoPaymentCount);
        model.addAttribute("paymentCount", paymentCount);

        return "dashboard";
    }
}

