package com.os.service;

import com.os.dto.CustomUserDetails;
import com.os.dto.InsertDTO;
import com.os.dto.ProductDTO;
import com.os.entity.*;
import com.os.repository.*;
import com.os.util.AutoStatus;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final AutoPaymentRepository autopaymentRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository, ProductRepository productRepository, CustomerRepository customerRepository, AutoPaymentRepository autopaymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.autopaymentRepository = autopaymentRepository;
        this.userRepository = userRepository;
    }

    public void insert_basic(InsertDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        Optional<User> userOptional = userRepository.findById(userId);



        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Customer customer = new Customer();
            customer.setCustomerName(dto.getCustomerName());
            customer.setCustomerEmail(dto.getCustomerEmail());
            customer.setCustomerPhone(dto.getCustomerPhone());
            customer.setCustomerAddress(dto.getCustomerAddress());

            customer.setUser(user);

            customerRepository.save(customer);

            Payment payment = new Payment();
            payment.setPaymentTitle(dto.getPaymentTitle());
            payment.setPaymentType(dto.getPaymentType());
            payment.setPaymentBizTo(dto.getPaymentBizTo());
            payment.setPaymentCreateTime(dto.getPaymentCreateTime());
            payment.setPaymentStatus(OrderStatus.wait);
            payment.setPaymentPayYn('N');
            payment.setPaymentDelYn('N');
            payment.setCustomer(customer);
            if(dto.getPaymentType().equals(OrderType.auto)) {


                AutoPayment autoPayment = new AutoPayment();

                autoPayment.setAutoMonth(dto.getAutoMonth());

                payment.setAutoPayment(autoPayment);
                LocalDateTime date = autoPayment.calculateLocalDateTime(dto);
                autoPayment.setAutoCreateTime(date);
                autoPayment.setPayment(payment);
                autoPayment.setAutoPay(dto.getAutoPay());
                autoPayment.setAutoStatus(AutoStatus.auto);
                /*autoPayment.setAutoPayCount(0);*/


                System.out.println(autoPayment.getPayment());
                autopaymentRepository.save(autoPayment);

            }

            paymentRepository.save(payment);
            for (ProductDTO productDTO : dto.getProductList()) {
                Product product = new Product();
                product.setProductName(productDTO.getProductName());
                product.setProductTotalItems(productDTO.getProductTotalItems());
                product.setProductPrice(productDTO.getProductPrice());
                product.setProductAmount(productDTO.getProductAmount());

                product.setPayment(payment);

                productRepository.save(product);
            }




        }



    }

}
