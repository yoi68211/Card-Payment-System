package com.os.service;

import com.os.dto.AllPaymentListDto;
import com.os.entity.Payment;
import com.os.repository.AllPaymentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllPaymentListService {

    private final AllPaymentListRepository allPaymentListRepository;

    @Autowired
    public AllPaymentListService(AllPaymentListRepository allPaymentListRepository) {
        this.allPaymentListRepository = allPaymentListRepository;
    }

    public List<AllPaymentListDto> findAll() {
        List<Payment> payEntityList = allPaymentListRepository.findAll();
        List<AllPaymentListDto> payDtoList = new ArrayList<>();
        for(Payment payment: payEntityList) {
            payDtoList.add(AllPaymentListDto.toAllPaymentListDto(payment));
        }
        return payDtoList;
    }
}