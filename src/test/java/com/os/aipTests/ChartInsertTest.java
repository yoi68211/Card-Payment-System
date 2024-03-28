package com.os.aipTests;


import com.os.dto.ProductDTO;
import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.entity.User;
import com.os.repository.CustomerRepository;
import com.os.repository.PaymentRepository;
import com.os.repository.ProductRepository;
import com.os.repository.UserRepository;
import com.os.service.UserService;
import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import com.os.util.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
public class ChartInsertTest {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    void chart() {
        Optional<User> userOp = userRepository.findById(1L);
        LocalDateTime currentTime = LocalDateTime.now();
        if(userOp.isPresent()) {
            User user = userOp.get();

            Random random = new Random();
            for (int i = 1; i < 500; i++) {
                // Customer 생성
                Customer customer = new Customer();
                customer.setCustomerName(i+"찬신");
                customer.setCustomerEmail("test"+i+"@naver.com");
                customer.setCustomerPhone("0101111"+i);
                customer.setCustomerAddress("숭의"+i+"동");
                customer.setCreateTime(currentTime);

                Payment payment = new Payment();
                payment.setPaymentTitle("테스트제목"+i);
                payment.setPaymentType(OrderType.basic);
                payment.setPaymentBizTo(BizTo.BtoB);
                payment.setPaymentDelYn('N');
                payment.setPaymentStatus(OrderStatus.wait);

                // 월은 1부터 12까지의 랜덤한 값 설정
                int month = random.nextInt(12) + 1;
                // 일은 1부터 28까지의 랜덤한 값 설정 (월에 따라서 최대 일수가 달라질 수 있으니 적절히 수정하세요)
                int day = random.nextInt(28) + 1;

                // LocalDateTime으로 현재 시간을 가져옴
// 시간 설정
                LocalDateTime createTime = LocalDateTime.of(currentTime.getYear(), month, day, currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
                payment.setCreateTime(createTime);


                List<Product> productList = new ArrayList<>();
                for (int j = 1; j <= 3; j++) {
                    Product product = new Product();
                    product.setProductName("상품"+j);
                    product.setProductTotalItems(i);
                    product.setProductPrice(j);
                    product.setProductAmount(j*i);
                    productList.add(product);
                    product.setCreateTime(currentTime);
                    product.setPayment(payment);

                }

                payment.setProducts(productList);
                customer.setUser(user);
                payment.setCustomer(customer);
                customer.setPayments(payment);

                customerRepository.save(customer);
            }
        }
    }

}


