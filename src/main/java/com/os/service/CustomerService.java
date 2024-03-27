package com.os.service;

import com.os.dto.CustomerDTO;
import com.os.dto.PaymentDetailsDTO;
import com.os.entity.Customer;
import com.os.repository.CustomerRepository;
import com.os.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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
    public List<CustomerDTO> thisMonthPaidAll(){
        LocalDateTime now = LocalDateTime.now();
        YearMonth thisMonth = YearMonth.from(now);
        LocalDateTime startOfMonth = thisMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = thisMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Customer> customers = customerRepository.findByAndPayments_CreateTimeBetween(startOfMonth, endOfMonth);

        List<CustomerDTO> customerDtoList = new ArrayList<>(); // Initialize the list


        for (Customer customer : customers) {

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(customer.getCustomerName());
            customerDTO.setEmail(customer.getCustomerEmail());
            customerDTO.setAddress(customer.getCustomerAddress());
            customerDTO.setPhone(customer.getCustomerPhone());

            customerDtoList.add(customerDTO);
        }

//        long count = customerRepository.countByPayments_CreateTimeBetween(startOfMonth, endOfMonth);
//        System.out.println("Count: " + count);
//
        return customerDtoList;

    }





    public long thisMonthInsertCount(){
        LocalDateTime now = LocalDateTime.now();
        YearMonth thisMonth = YearMonth.from(now);
        LocalDateTime startOfMonth = thisMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = thisMonth.atEndOfMonth().atTime(23, 59, 59);



        return customerRepository.countByPayments_CreateTimeBetween(startOfMonth, endOfMonth);
    }



    public long countByCustomersByPaid() {

        return customerRepository.countByPayments_PaymentStatus(OrderStatus.paid);
    }




    public List<CustomerDTO> getAllCustomersByPaid() {

        List<Customer> paidCustomers = customerRepository.findByPayments_PaymentStatus(OrderStatus.paid);

        List<CustomerDTO> customerDTOS =new ArrayList<>();
        for (Customer customer: paidCustomers ){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(customer.getCustomerName());
            customerDTO.setEmail(customer.getCustomerEmail());
            customerDTO.setAddress(customer.getCustomerAddress());
            customerDTO.setPhone(customer.getCustomerPhone());


            customerDTOS.add(customerDTO);

        }

        return customerDTOS;
    }





}
