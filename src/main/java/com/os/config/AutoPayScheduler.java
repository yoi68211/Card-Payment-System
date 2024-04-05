package com.os.config;

import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.repository.AutoPaymentRepository;
import com.os.repository.PaymentRepository;
import com.os.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class AutoPayScheduler {

    private final PaymentRepository paymentRepository;
    private final AutoPaymentRepository autoPaymentRepository;

    @Transactional
    @Scheduled(fixedDelay = 300000) // 5분마다 실행
    public void autoPaymentUpdate() {
        // payment type == auto 인 payment 와 auto_payment 리스트 road
        List<Payment> payments = paymentRepository.findAllPaymentsWithAutoPayments();
        LocalDateTime currentDateTime = LocalDateTime.now(); // 현재 날짜


        for(Payment payment : payments){

            // auto_payments 의 현재날까가 next_time 의 날짜를 지났다면
            if (payment.getAutoPayments().getPaymentNextTime().isBefore(currentDateTime)
                    || payment.getAutoPayments().getPaymentNextTime().isEqual(currentDateTime)) {

                // update count +1 next_time update
                int updatedAutoPayCount = payment.getAutoPayments().getAutoPayCount() + 1;
                payment.getAutoPayments().setAutoPayCount(updatedAutoPayCount); // 결제 횟수
                payment.getAutoPayments().setAutoPayDate(LocalDate.now()); // 결제 날짜
                payment.getAutoPayments().setPaymentNextTime(Payment.calculateLocalDateTime(payment.getPaymentMonth(),payment.getPaymentAutoDate()));
                payment.setPaymentStatus(OrderStatus.paid);
                paymentRepository.save(payment);

            }
        }




    }
}
