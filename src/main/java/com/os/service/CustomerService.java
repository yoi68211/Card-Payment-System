package com.os.service;

import com.os.dto.PaymentDetailsDTO;
import com.os.entity.Customer;
import com.os.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


            return  PaymentDetailsDTO.builder()
                    .customer(customer)
                    .build();



        }

        throw new NoSuchElementException("객체가 null 이에요!");
    }

}
