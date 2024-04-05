package com.os.controller;

//import com.os.config.ApiKeyProperties;
import com.os.config.ApiKeyProperties;
import com.os.dto.PaymentDetailsDTO;
import com.os.service.AutoPaymentService;
import com.os.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Controller
@RequiredArgsConstructor
@RequestMapping("/toss")
public class WidgetController {
    private final CustomerService customerService;
    private final AutoPaymentService autoPaymentService;
    private ApiKeyProperties apiKeyProperties;

    @Autowired
    public void ApiController(ApiKeyProperties apiKeyProperties) {
        this.apiKeyProperties = apiKeyProperties;
    }









//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/confirm")
    public ResponseEntity<org.json.simple.JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
        String secretKey = apiKeyProperties.getSecretKey();
        
        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            org.json.simple.JSONObject requestData = (org.json.simple.JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        // TODO: 개발자센터에 로그인해서 내 결제위젯 연동 키 > 시크릿 키를 입력하세요. 시크릿 키는 외부에 공개되면 안돼요.
        // @docs https://docs.tosspayments.com/reference/using-api/api-keys


        System.out.println("YAML 변수값 ==> " + secretKey);



        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/payment-widget/integration#3-결제-승인하기
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);


        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // TODO: 결제 성공 및 실패 비즈니스 로직을 구현하세요.


        Long id = Long.valueOf(orderId.replace("order_test", ""));
        //customerService.UpdatePaid(id);

        autoPaymentService.UpdatePaid(id);



        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        org.json.simple.JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();

        return ResponseEntity.status(code).body(jsonObject);
    }


    @GetMapping("/checkout")
    public String toss(@RequestParam Long id ,Model model) throws Exception {

        String clientKey = apiKeyProperties.getClientKey();
        PaymentDetailsDTO paymentDetailsDTO = customerService.getDetails(id);
        model.addAttribute("paymentDetailsDTO",paymentDetailsDTO);
        model.addAttribute("clientKey",clientKey);

        return "/toss/checkout";
    }


    @GetMapping("/success")
    public String paymentRequest(HttpServletRequest request, Model model) throws Exception {

        return "/toss/success";
    }
    @GetMapping("/fail")
    public String failPayment(HttpServletRequest request, Model model) throws Exception {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "/toss/fail";
    }




}
