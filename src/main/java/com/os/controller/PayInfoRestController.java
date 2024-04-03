package com.os.controller;

import com.os.dto.IssueBillingReq;
import com.os.dto.PaymentDTOC;
import com.os.dto.UpdateDTO;
import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.service.AutoPaymentService;
import com.os.service.PaymentServiceC;
import com.os.service.UpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PayInfoRestController {
    private final UpdateService updateService;
    private final PaymentServiceC paymentService;
    private final AutoPaymentService autoPaymentService;

    @PostMapping("/payDetailEdit")
    public void updateDetail(@RequestBody @Valid UpdateDTO updateDTO){
        boolean check = updateService.update(updateDTO);
    }

    @PostMapping("/payDetailDel")
    public void deleteDetail(@RequestBody @Valid UpdateDTO updateDTO){
        System.out.println("삭제 실행 => " + updateDTO.getPaymentId());

        boolean check = updateService.delUpdate(updateDTO);

    }


    @PostMapping("/basicPayPaid")
    public void basicPayPaid(@RequestParam Long id){
        System.out.println("결제성공 => " + id);
        Payment payment = paymentService.basicPayPaid(id);
        int month = payment.getPaymentMonth();
        int date = payment.getPaymentAutoDate();
        boolean check = autoPaymentService.autoTableInsert(payment, month, date);
        if(check){
            System.out.println("수정성공");
        } else{
            System.out.println("수정실패");
        }
    }

    @PostMapping("/basicPayError")
    public void basicPayError(@RequestParam Long id){
        System.out.println("결제실패 => " + id);
        boolean check = paymentService.basicPayError(id);

        if(check){
            System.out.println("수정성공");
        } else{
            System.out.println("수정실패");
        }
    }


//    @PostMapping("/issue-billing")
//    public BaseResponse<IssueBillingRes> issueBilling(@RequestBody IssueBillingReq issueBillingReq){
//        try{
//            //int customerIdx = jwtService.getUserIdx();
//            IssueBillingRes issueBillingRes = paymentService.issueBilling(issueBillingReq);
//            return new BaseResponse<>(issueBillingRes);
//        }catch (BaseException baseException){
//            return new BaseResponse<>(baseException.getStatus());
//        }
//    }
}
