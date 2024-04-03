package com.os.service;

import com.os.entity.AutoPayment;
import com.os.entity.Payment;
import com.os.repository.AutoPaymentRepository;
import com.os.util.AutoStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class AutoPaymentService {
    private final AutoPaymentRepository autoPaymentRepository;

    @Transactional
    public boolean autoTableInsert(Payment payment, int month, int date){
        LocalDateTime now = calculateLocalDateTime(month, date);
        AutoPayment autoPayment = new AutoPayment();
        autoPayment.setAutoStatus(AutoStatus.auto);
        autoPayment.setAutoPayCount(1);
        autoPayment.setPaymentNextTime(now);
        autoPayment.setPayment(payment);
        autoPaymentRepository.save(autoPayment);
        return true;
    }

    public LocalDateTime calculateLocalDateTime(int month, int date){
        LocalDate currentDate = LocalDate.now();
        LocalDate calculateDate = currentDate.plusMonths(month).withDayOfMonth(date);
        return LocalDateTime.of(calculateDate, LocalTime.MIN);
    }
}
