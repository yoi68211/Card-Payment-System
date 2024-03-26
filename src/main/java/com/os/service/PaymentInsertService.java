package com.os.service;

import com.os.dto.InsertDTO;
import com.os.dto.ProductDTO;
import com.os.entity.*;
import com.os.repository.CustomerRepository;
import com.os.repository.PaymentRepository;
import com.os.repository.ProductRepository;
import com.os.util.OrderStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
public class PaymentInsertService {
    private final CustomerRepository customerRepository;

    public void insert_basic(InsertDTO dto ,User user) {


                Customer customer = new Customer();
                customer.setCustomerName(dto.getCustomerName());
                customer.setCustomerEmail(dto.getCustomerEmail());
                customer.setCustomerPhone(dto.getCustomerPhone());
                customer.setCustomerAddress(dto.getCustomerAddress());

                Payment payment = new Payment();
                payment.setPaymentTitle(dto.getPaymentTitle());
                payment.setPaymentType(dto.getPaymentType());
                payment.setPaymentBizTo(dto.getPaymentBizTo());
                payment.setCreateTime(dto.getPaymentCreateTime());
                payment.setPaymentStatus(OrderStatus.wait);

                List<Product> productList = new ArrayList<>();
                for (ProductDTO productDTO : dto.getProductList()) {
                    Product product = new Product();
                    product.setProductName(productDTO.getProductName());
                    product.setProductTotalItems(productDTO.getProductTotalItems());
                    product.setProductPrice(productDTO.getProductPrice());
                    product.setProductAmount(productDTO.getProductAmount());
                    productList.add(product);
                    product.setPayment(payment);

                }

                payment.setProducts(productList);
                customer.setUser(user);
                payment.setCustomer(customer);
                customer.setPayments(payment);

                customerRepository.save(customer);

            }

        }


