package com.os.controller;

import com.os.dto.AllPaymentListDto;
import com.os.dto.DetailedSearchDTO;
import com.os.dto.PaymentDetailsDTO;
import com.os.service.AllPaymentListService;
import com.os.service.CustomerService;
import com.os.service.PaymentServiceC;
import com.os.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    public final UserService userService;
    public final AllPaymentListService allPaymentListService;
    private final CustomerService customerService;
    private final PaymentServiceC paymentService;

    @GetMapping
    public String index() {
        return "login";
    }


    @GetMapping("dashboard")
    public String login(Model model) {
        LocalDateTime currentTime = LocalDateTime.now();
        int month = currentTime.getMonthValue();
        List<Object[]> monthChart = paymentService.getCountByDateInMonth(month);
        System.out.println("=========================================");
        for(Object[] list : monthChart){
            System.out.println("-----------------------");
            System.out.println("date = " + list[0]);
            System.out.println("count = " + list[1]);

        }
        System.out.println("=========================================");
        model.addAttribute("monthChartList",monthChart);
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


    @PostMapping("/search")
    public String search(@ModelAttribute("form") DetailedSearchDTO searchDTO, Model model, @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AllPaymentListDto> allPaymentsPage = allPaymentListService.detailSearch(searchDTO, pageable);
        long payCount = allPaymentsPage.getTotalElements();

        model.addAttribute("payList", allPaymentsPage);
        model.addAttribute("payCount", payCount);
        return "list";
    }





}
