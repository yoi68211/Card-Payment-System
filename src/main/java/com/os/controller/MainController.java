package com.os.controller;

import com.os.dto.AllPaymentListDto;
import com.os.dto.MemoDTO;
import com.os.dto.PaymentDetailsDTO;
import com.os.entity.User;
import com.os.service.AllPaymentListService;
import com.os.service.CustomerService;
import com.os.service.MemoService;
import com.os.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final MemoService memoService;

    @GetMapping
    public String index() {
        return "login";
    }


    @GetMapping("dashboard")
    public String login(Model model) {


        model.addAttribute("paymentSuccessCount", customerService.countByCustomersByPaid());
        model.addAttribute("paymentInsertThisMonth", customerService.thisMonthInsertCount());

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
    public String findAll(Model model, @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "keyword", required = false) String keyword) {
        Page<AllPaymentListDto> allPaymentsPage;

        if (keyword != null && !keyword.isEmpty()) {
            allPaymentsPage = allPaymentListService.findByTitleContaining(keyword, pageable);
        } else {
            allPaymentsPage = allPaymentListService.findAll(pageable);
        }

        long payCount = allPaymentsPage.getTotalElements();
        model.addAttribute("payList", allPaymentsPage);
        model.addAttribute("payCount", payCount);
        model.addAttribute("keyword", keyword);
        return "list";
    }

}
