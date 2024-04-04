package com.os.config;

import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.repository.AutoPaymentRepository;
import com.os.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class AutoPayScheduler {

    private final PaymentRepository paymentRepository;
    private final AutoPaymentRepository autoPaymentRepository;
    @Scheduled(fixedDelay = 300000) // 5분마다 실행
    public void autoPaymentUpdate() {
        // payment type == auto 인 payment 와 auto_payment 리스트 road
        List<Payment> payments = paymentRepository.findAllPaymentsWithAutoPayments();
        LocalDateTime currentDateTime = LocalDateTime.now(); // 현재 날짜
        System.out.println("currentDateTime = " + currentDateTime);
        for(Payment list : payments){
            System.out.println("--------------payment--------------------------");
            System.out.println("payment ID ===>   " + list.getId());
            System.out.println("payment title ==> " + list.getPaymentTitle());
            System.out.println("--------------autoPayment----------------------");
            System.out.println("autoPayment 다음결제일 ==> "+list.getAutoPayments().getPaymentNextTime());
            System.out.println("autoPayment ID ===>  = " + list.getAutoPayments().getId());
            // auto_payments 의 현재날까가 next_time 의 날짜를 지났다면
            if (list.getAutoPayments().getPaymentNextTime().isBefore(currentDateTime)
                    || list.getAutoPayments().getPaymentNextTime().isEqual(currentDateTime)) {
                // update count +1 next_time update
                int updatedAutoPayCount = list.getAutoPayments().getAutoPayCount() + 1;
                list.getAutoPayments().setAutoPayCount(updatedAutoPayCount);
                autoPaymentRepository.save(list.getAutoPayments());
                System.out.println("다음 결제일이 지났거나 현재 날짜입니다.");
            }
        }




    }
}
