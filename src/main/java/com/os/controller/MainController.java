package com.os.controller;

import com.os.dto.AllPaymentListDto;
import com.os.dto.DetailedSearchDTO;
import com.os.dto.MemoDTO;
import com.os.dto.PaymentDetailsDTO;
import com.os.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    public final UserService userService;
    public final AllPaymentListService allPaymentListService;
    private final CustomerService customerService;
    private final PaymentServiceC paymentService;
    private final MemoService memoService;


    @GetMapping
    public String index() {
        return "login";
    }

    @GetMapping("dashboard")
    public String mainPage(Model model) {


        List<Long> monthChart = paymentService.getCountsByMonthRange();
        List<Long> yearChart = paymentService.getCountsByYearRange();

        List<MemoDTO> memoList = memoService.findAll(); // MemoService를 사용하여 모든 메모를 가져옵니다.
        model.addAttribute("MemoList", memoList);

        model.addAttribute("monthChartList",monthChart);
        model.addAttribute("yearChartList",yearChart);
        model.addAttribute("paymentSuccessCount", customerService.countByCustomersByPaid());
        model.addAttribute("paymentInsertThisMonth", customerService.thisMonthInsertCount());


        return "dashboard";
    }

    @GetMapping("/chartDetail")
    public String chartDetail(Model model){
        List<Long> monthChart = paymentService.getCountsByMonthRange();
        List<Long> yearChart = paymentService.getCountsByYearRange();

        model.addAttribute("monthChartList",monthChart);
        model.addAttribute("yearChartList",yearChart);
        return "chartDetail";
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

    /*
        @method : insert_form
        @desc : /insert_form 으로 이동
        @author : 김성민
    */
    @GetMapping("insert_form")
    public String insert_form(){
        return "insert_form";
    }


    @GetMapping("/list")
    public String findAll(Model model, @PageableDefault(page = 0, size = 10, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "keyword", required = false) String keyword) {
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

    @PostMapping("/delete/{id}")
    public String deletePayment(@PathVariable String id) {
        Long paymentId = Long.parseLong(id);
        allPaymentListService.updatePaymentDelYnById(paymentId);
        return "redirect:/list";
    }


    @GetMapping("/search")
    public String search(@ModelAttribute("form") DetailedSearchDTO searchDTO, Model model, @PageableDefault(page = 0, size = 10, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AllPaymentListDto> allPaymentsPage = allPaymentListService.detailSearch(searchDTO, pageable);
        long payCount = allPaymentsPage.getTotalElements();

        System.out.println("payCount = " + payCount);

        model.addAttribute("payList", allPaymentsPage);
        model.addAttribute("payCount", payCount);
        model.addAttribute("searchDTO",searchDTO);

        return "search";
    }




}
