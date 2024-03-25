package com.os.service;

import com.os.dto.PaymentDTOC;
import com.os.entity.Payment;
import com.os.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentServiceC {

    private final PaymentRepository paymentRepository;

    public PaymentDTOC paymentRoad(Long id){
        Optional<Payment> paymentOptional = paymentRepository.findByCustomerIdAndPaymentDelYn(id, 'N');

        if(paymentOptional.isPresent()){
            Payment payment = paymentOptional.get();

            return PaymentDTOC.payInfoDTO(payment);
        } else{
            return null;
        }
    }
}
