package com.os.service;

import com.os.dto.AllPaymentListDto;
import com.os.entity.Payment;
import com.os.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AllPaymentListService {

    private final PaymentRepository paymentRepository;

    public AllPaymentListService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Page<AllPaymentListDto> findAll(Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentDelYn('N', pageable);
        return allPaymentsPage.map(AllPaymentListDto::toAllPaymentListDto);
    }

    public Page<AllPaymentListDto> findByTitleContaining(String keyword, Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentTitleContainingAndPaymentDelYn(keyword, 'N', pageable);
        return allPaymentsPage.map(AllPaymentListDto::toAllPaymentListDto);
    }

    public void updatePaymentDelYnById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다 id : " + id));
        payment.setPaymentDelYn('Y');
        paymentRepository.save(payment);
    }

}