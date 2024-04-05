package com.os.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.dto.AutoPaymentDTOC;
import com.os.dto.CustomerDTOC;
import com.os.dto.PaymentDTOC;
import com.os.dto.ProductDTOC;
import com.os.service.AutoPaymentService;
import com.os.service.CustomerServiceC;
import com.os.service.PaymentServiceC;
import com.os.service.ProductServiceC;
import com.os.util.OrderType;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Controller
public class PayInfoController {

    private final CustomerServiceC customerService;
    private final PaymentServiceC paymentService;
    private final ProductServiceC productService;
    private final AutoPaymentService autoPaymentService;

    @GetMapping("/payInfoDetail/")
    public String payInfoDetail(@RequestParam long id, Model model) {

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

    @GetMapping("/payAutoDetail/")
    public String payAutoDetail(@RequestParam long id, Model model) {

        CustomerDTOC customerInfo = customerService.customerRoad(id);
        PaymentDTOC payInfo = paymentService.paymentRoad(customerInfo.getId());
        AutoPaymentDTOC autoPayInfo = autoPaymentService.autoPayRoad(payInfo.getId());
        if(autoPayInfo == null){
            return "redirect:/dashboard";
        }

        List<ProductDTOC> productInfo = productService.productRoad(payInfo.getId());
        model.addAttribute("customerInfo", customerInfo);
        model.addAttribute("payInfo", payInfo);
        model.addAttribute("productInfo", productInfo);
        model.addAttribute("autoPayInfo", autoPayInfo);

        return "paylist/autoPayInfoDetail";

    }

    @GetMapping("/receipt/")
    public String receipt(@RequestParam("paymentId") Long paymentId,
                          @RequestParam("customerId") Long customerId,
                          Model model) throws IOException {
        String orderId = "order_test"+paymentId;

        String widgetSecretKey = "test_sk_LkKEypNArW2PPmGx2XpQVlmeaxYG";

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // 시크릿키 encoding
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/payment-widget/integration#3-결제-승인하기
        URL url = new URL("https://api.tosspayments.com/v1/payments/orders/"+orderId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        // 응답 코드 확인
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();



// 모델에 추가



        CustomerDTOC customerInfo = customerService.customerRoad(customerId);
        PaymentDTOC payInfo = paymentService.paymentRoad(customerInfo.getId());
        List<ProductDTOC> productInfo = productService.productRoad(payInfo.getId());


        model.addAttribute("cardInfo", jsonResponse);
        model.addAttribute("customerInfo", customerInfo);
        model.addAttribute("payInfo", payInfo);
        model.addAttribute("productInfo", productInfo);
        return "paylist/receipt";
    }

}
