package com.os.service;

import com.os.dto.InsertDTO;
import com.os.dto.ProductDTO;
import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.entity.User;
import com.os.repository.CustomerRepository;
import com.os.repository.PaymentRepository;
import com.os.repository.ProductRepository;
import com.os.repository.UserRepository;
import com.os.security.CustomUserDetails;
import com.os.util.OrderStatus;
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
    private final UserRepository userRepository;



    public PaymentService(PaymentRepository paymentRepository, ProductRepository productRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public boolean insert_basic(InsertDTO dto) {

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

            /*if(dto.getPaymentMonth() != 0) {
                payment.setPaymentMonth(dto.getPaymentMonth());
            }*/
            if(dto.getAutoDate() != 0) {
                LocalDateTime date = payment.calculateLocalDateTime(dto);
                payment.setPaymentNextTime(date);
            }
            /*if(dto.getPaymentFirstPay() != 0) {
                payment.setPaymentFirstPay(dto.getPaymentFirstPay());
            }*/
            payment.setPaymentDelYn('N');
            payment.setCustomer(customer);

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

        return true;
        }else {
            return false;
        }
    }
}
