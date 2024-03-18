package com.os.service;

import com.os.dto.CustomUserDetails;
import com.os.dto.InsertDTO;
import com.os.dto.ProductDTO;
import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.repository.CustomerRepository;
import com.os.repository.PaymentRepository;
import com.os.repository.ProductRepository;
import com.os.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
//    private final ProductRepository productRepository;
//    private final CustomerRepository customerRepository;


    public void insert(InsertDTO dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        Payment payment = new Payment();
        payment.setDocumentNo(dto.getDocumentNo());
        payment.setTitle(dto.getTitle());
        payment.setType(dto.getType());
        payment.setFirstPay(dto.getFirstPay());
        payment.setBizTo(dto.getBizTo());
        payment.setCycle(dto.getCycle());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPay(dto.getPay());
        payment.setMemo(dto.getMemo());
        payment.setCreateTime(dto.getCreateTime());
        payment.setDelYn('N');
        payment.setUserId(userId);

        paymentRepository.save(payment);

////////////////////////////////////////////////////////////////////

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
//        customer.setPayment(payment);

//        customerRepository.save(customer);

////////////////////////////////////////////////////////////////////

        for (ProductDTO productDTO : dto.getProductList()) {
            Product product = new Product();
            product.setProductName(productDTO.getProductName());
            product.setTotalItems(productDTO.getTotalItems());
//            product.setProductAmount(productDTO.getProductAmount());
            product.setPrice(productDTO.getPrice());
            product.setPayment(payment);

//            productRepository.save(product);
        }

    }
}
