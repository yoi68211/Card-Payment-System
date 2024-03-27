package com.os.service;

import com.os.dto.AllPaymentListDto;
import com.os.entity.Payment;
import com.os.repository.AllPaymentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AllPaymentListService {

    private final AllPaymentListRepository allPaymentListRepository;

    public AllPaymentListService(AllPaymentListRepository allPaymentListRepository) {
        this.allPaymentListRepository = allPaymentListRepository;
    }

    public Page<AllPaymentListDto> findAll(Pageable pageable) {
        Page<Payment> allPaymentsPage = allPaymentListRepository.findByPaymentDelYnNot('Y', pageable);
        return allPaymentsPage.map(AllPaymentListDto::toAllPaymentListDto);
    }

    public Page<AllPaymentListDto> findByTitleContaining(String keyword, Pageable pageable) {
        Page<Payment> allPaymentsPage = allPaymentListRepository.findByPaymentTitleContainingAndPaymentDelYnNot(keyword, 'Y', pageable);
        return allPaymentsPage.map(AllPaymentListDto::toAllPaymentListDto);
    }

}