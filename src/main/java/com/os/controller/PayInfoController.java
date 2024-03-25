package com.os.controller;

import com.os.dto.CustomerDTOC;
import com.os.dto.PaymentDTOC;
import com.os.dto.ProductDTOC;
import com.os.service.CustomerServiceC;
import com.os.service.PaymentServiceC;
import com.os.service.ProductServiceC;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Controller
public class PayInfoController {

    private final CustomerServiceC customerService;
    private final PaymentServiceC paymentService;
    private final ProductServiceC productService;

    @GetMapping("/payInfoDetail/{id}")
    public String payInfoDetail(@PathVariable long id, Model model) {
        System.out.println("받은 id =>" + id);
        CustomerDTOC customerInfo = customerService.customerRoad(id);
        PaymentDTOC payInfo = paymentService.paymentRoad(customerInfo.getId());
        if(payInfo == null){
            return "redirect:/dashboard";
        }

        List<ProductDTOC> productInfo = productService.productRoad(payInfo.getId());
        model.addAttribute("customerInfo", customerInfo);
        model.addAttribute("payInfo", payInfo);
        model.addAttribute("productInfo", productInfo);

        return "paylist/basicPayInfoDetail";


    }


}
