package com.os.service;

import com.os.dto.AutoPaymentDTOC;
import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.repository.AutoPaymentRepository;
import com.os.repository.PaymentRepository;
import com.os.util.AutoOrderStatus;
import com.os.util.AutoStatus;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class    AutoPaymentService {
    private final AutoPaymentRepository autoPaymentRepository;
    private final PaymentRepository paymentRepository;

    public void UpdatePaid(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isPresent()){

            Payment payment = paymentOptional.get();
            payment.setPaymentStatus(OrderStatus.paid);

            if(payment.getPaymentType()== OrderType.auto) {

                AutoPayment autoPayment = new AutoPayment();
                autoPayment.setAutoStatus(AutoStatus.paid);
                autoPayment.setAutoPayCount(1);
                autoPayment.setPaymentNextTime(Payment.calculateLocalDateTime(payment.getPaymentMonth(),payment.getPaymentAutoDate()));
                autoPayment.setAutoPayDate(LocalDate.now());
                autoPayment.setAutoOrderStatus(AutoOrderStatus.paid);
                autoPayment.setPayment(payment);

                autoPaymentRepository.save(autoPayment);
            }
            paymentRepository.save(payment);
        }
    }

    public long autoSuccess(LocalDateTime startDate, LocalDateTime endDate){
        return autoPaymentRepository.countByAutoStatusAndUpdateTimeBetween(AutoStatus.paid, startDate, endDate);
    }

    public long autoStop(LocalDateTime startDate, LocalDateTime endDate){
        return autoPaymentRepository.countByAutoStatusAndUpdateTimeBetween(AutoStatus.stop, startDate, endDate);
    }

    public long autoError(LocalDateTime startDate, LocalDateTime endDate){
        return autoPaymentRepository.countByAutoStatusAndUpdateTimeBetween(AutoStatus.error, startDate, endDate);
    }

    public long autoAll(LocalDateTime startDate, LocalDateTime endDate){
        return autoPaymentRepository.countByUpdateTimeBetween(startDate, endDate);
    }

    public AutoPaymentDTOC autoPayRoad(Long id){
        Optional<AutoPayment> autoPaymentOptional = autoPaymentRepository.findByPaymentId(id);
        if(autoPaymentOptional.isPresent()){
            AutoPayment autoPayment = autoPaymentOptional.get();
            AutoPaymentDTOC autoPayInfo = AutoPaymentDTOC.autoPaymentInfoDTO(autoPayment);
            return autoPayInfo;
        }
        return null;
    }
}


