package com.os.service;

import com.os.dto.CustomerDTOC;
import com.os.entity.Customer;
import com.os.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceC {
    private final CustomerRepository customerRepository;

    public CustomerDTOC customerRoad(Long id){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();

            return CustomerDTOC.CustomerInfoDTO(customer);
        } else{
            return null;
        }
    }
}
