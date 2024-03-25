package com.os.controller;

import com.os.dto.AllPaymentListDto;
import com.os.dto.PaymentDetailsDTO;
import com.os.entity.User;
import com.os.service.AllPaymentListService;
import com.os.service.CustomerService;
import com.os.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    public final UserService userService;
    public final AllPaymentListService allPaymentListService;
    private final CustomerService customerService;

    @GetMapping
    public String index() {
        return "login";
    }
    @GetMapping("dashboard")
    public String login() {
        return "dashboard";
    }


    @GetMapping("/join")
    public String joinPage()   {

        return "join";

    }


    /*
    TODO: 2024-03-22 "paymentDetails.html" In the QR creation logic, I couldn't tell which code uses which code API, so I entered a random URL
          Please implement by adding payload to Payment Details DTO.
    */
    @GetMapping("/paymentDetails")
    public String paymentDetailsPage(@RequestParam Long id , Model model)   {



        PaymentDetailsDTO paymentDetailsDTO = customerService.getDetails(id);
        model.addAttribute("paymentDetailsDTO",paymentDetailsDTO);


        return "paymentDetails";

    }

    @GetMapping("pageSample")
    public String pageSample() {
        return "/pageSample";
    }

    @GetMapping("insert_form")
    public String insert_form(){
        return "insert_form";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<AllPaymentListDto> allPayments = allPaymentListService.findAll();
        model.addAttribute("payList", allPayments);
        return "list";
    }

}
