package com.os.service;

import com.os.dto.PaymentDTOC;
import com.os.entity.Payment;
import com.os.repository.PaymentRepository;
import com.os.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public List<Long> getCountsByMonthRange() {

        LocalDateTime startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDate = LocalDateTime.now().withDayOfMonth(LocalDateTime.now().toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        List<Long> dailyCounts = new ArrayList<>();


        LocalDate currentDate = startDate.toLocalDate();

        while (!currentDate.isAfter(endDate.toLocalDate())) {
            Long count = paymentRepository.countPaymentsByDate(currentDate);
            dailyCounts.add(count != null ? count : 0L);
            currentDate = currentDate.plusDays(1);
        }

        return dailyCounts;
    }

    public List<Long> getCountsByYearRange() {
        int nowYear = LocalDateTime.now().getYear(); // 현재 년도

        List<Long> monthCounts = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            LocalDate startDate = LocalDate.of(nowYear, i, 1); // 해당 월의 시작일
            LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // 해당 월의 마지막 날짜

            Long count = paymentRepository.countPaymentsYearByDateRange(startDate, endDate);
            monthCounts.add(count != null ? count : 0L);
        }

        return monthCounts;
    }


    public Payment basicPayPaid(Long paymentId){
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        if(paymentOptional.isPresent()){
            Payment payment = paymentOptional.get();
            payment.setPaymentStatus(OrderStatus.paid);

            return paymentRepository.save(payment);
        }
        return null;
    }

    public boolean basicPayError(Long paymentId){
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        if(paymentOptional.isPresent()){
            Payment payment = paymentOptional.get();
            payment.setPaymentStatus(OrderStatus.error);
            paymentRepository.save(payment);
            return true;
        }
        return false;
    }


}
