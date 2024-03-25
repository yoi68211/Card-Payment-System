package com.os.service;

import com.os.dto.PaymentDetailsDTO;
import com.os.entity.Customer;
import com.os.repository.CustomerRepository;
import com.os.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public PaymentDetailsDTO getDetails(Long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {

            Customer customer = customerOptional.get();


            return PaymentDetailsDTO.builder()
                    .customer(customer)
                    .build();

        }
        throw new NoSuchElementException("객체가 null 이에요!");
    }


    // 이번달 paid상태인 customer 조회 메서드 쿼리 입니다
    public List<Customer> thisMonthPaidSearch(){
        LocalDateTime now = LocalDateTime.now();
        YearMonth thisMonth = YearMonth.from(now);
        LocalDateTime startOfMonth = thisMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = thisMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Customer> customers = customerRepository.findByPayments_PaymentStatusAndPayments_CreateTimeBetween(OrderStatus.paid, startOfMonth, endOfMonth);
        for (Customer customer : customers) {

            System.out.println("customer id= " + customer.getId());
            System.out.println("customer name= " + customer.getCustomerName());

        }



        long count = customerRepository.countByPayments_PaymentStatusAndPayments_CreateTimeBetween(OrderStatus.paid, startOfMonth, endOfMonth);
        System.out.println("Count: " + count);


        return customers;

    }



}
