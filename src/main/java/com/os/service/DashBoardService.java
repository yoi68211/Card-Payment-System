package com.os.service;

import com.os.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DashBoardService {

    private final PaymentRepository paymentRepository;

    public DashBoardService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public long countErrorAutoPayments(LocalDateTime currentDate) {
        return paymentRepository.countErrorAutoPayments(currentDate);
    }

    public long countSuccessAutoPayments(LocalDateTime currentDate) {
        return paymentRepository.countSuccessAutoPayments(currentDate);
    }

    public long countWaitAutoPayments(LocalDateTime currentDate) {
        return paymentRepository.countWaitAutoPayments(currentDate);
    }

    public long countAutoPayments(LocalDateTime currentDate) {
        return paymentRepository.countAutoPayments(currentDate);
    }

    public long countPayments(LocalDateTime currentDate) {
        return paymentRepository.countPayments(currentDate);
    }

}
