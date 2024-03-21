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
import com.os.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class PaymentInsertService {
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    public final UserService userService;

    public boolean insert_basic(InsertDTO dto) {
        User user = userService.findId();

        if (user != null) {

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

            if(dto.getAutoDate() != 0) {
                LocalDateTime date = payment.calculateLocalDateTime(dto);
                payment.setPaymentNextTime(date);
            }
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
