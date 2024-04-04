package com.os.service;

import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.repository.AutoPaymentRepository;
import com.os.repository.PaymentRepository;
import com.os.util.AutoStatus;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AutoPaymentService {
    private final AutoPaymentRepository autoPaymentRepository;
    private final PaymentRepository paymentRepository;

    public void UpdatePaid(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isPresent()){

            Payment payment = paymentOptional.get();
            payment.setPaymentStatus(OrderStatus.paid);

            if(payment.getPaymentType()== OrderType.auto) {

                AutoPayment autoPayment = new AutoPayment();
                autoPayment.setAutoStatus(AutoStatus.auto);
                autoPayment.setAutoPayCount(1);
                autoPayment.setPaymentNextTime(payment.calculateLocalDateTime(payment.getPaymentMonth(),payment.getPaymentAutoDate()));
                autoPayment.setPayment(payment);

                autoPaymentRepository.save(autoPayment);
            }
            paymentRepository.save(payment);
        }
    }

    public long autoSuccess(LocalDateTime startDate, LocalDateTime endDate){

        return autoPaymentRepository.countByAutoStatusAndUpdateTimeBetween(AutoStatus.auto, startDate, endDate);
    }
    public long autoStop(LocalDateTime startDate, LocalDateTime endDate){

        return autoPaymentRepository.countByAutoStatusAndUpdateTimeBetween(AutoStatus.stop, startDate, endDate);
    }
}


