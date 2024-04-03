package com.os.controller;


import com.os.dto.UpdateDTO;
import com.os.service.AutoPaymentService;
import com.os.service.PaymentServiceC;
import com.os.service.UpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/basicPayError")
//    public void basicPayError(@RequestParam Long id){
//        System.out.println("결제실패 => " + id);
//        boolean check = paymentService.basicPayError(id);
//
//        if(check){
//            System.out.println("수정성공");
//        } else{
//            System.out.println("수정실패");
//        }
//    }


}
