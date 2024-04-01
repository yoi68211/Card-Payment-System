package com.os.aipTests;

import com.os.dto.MemoDTO;
import com.os.dto.ProductDTO;
import com.os.entity.*;
import com.os.repository.*;
import com.os.service.MemoService;
import com.os.service.PaymentServiceC;
import com.os.service.UserService;
import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import com.os.util.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
public class ChartInsertTest {

    @Autowired
    public PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentServiceC paymentService;
    @Autowired
    public MemoService memoService;
    @Autowired
    public MemoRepository memoRepository;


    @Test
    void softDeleteTest(){
        //memoRepository.softDeleteById(1L);
        List<Customer> entityList = customerRepository.findByPayments_PaymentDelYn('Y');
        for(Customer list : entityList){
            System.out.println(list.getPayments().getPaymentTitle());
        }
    }

    @Test
    void count(){

        // 차트 data 불러오기
//        List<Long> countList = paymentService.getCountsByYearRange();
//        int i=0;
//        for(Long count : countList){
//            i++;
//            System.out.println("Date: " + count);
//
//        }
//        System.out.println("12 맞누? ==>"+i);
    }

    @Test
    void chart() {
        Optional<User> userOp = userRepository.findById(1L);
        LocalDateTime currentTime = LocalDateTime.now();
        if(userOp.isPresent()) {
            User user = userOp.get();

            Random random = new Random();
            for (int i = 1; i < 500; i++) {
                // 월은 1부터 12까지의 랜덤한 값 설정
                int month = random.nextInt(12) + 1;
                // 일은 1부터 28까지의 랜덤한 값 설정 (월에 따라서 최대 일수가 달라질 수 있으니 적절히 수정하세요)
                int day = random.nextInt(28) + 1;
                LocalDateTime createTime = LocalDateTime.of(currentTime.getYear(), month, day, currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
                // Customer 생성
                Customer customer = new Customer();
                customer.setCustomerName(i+"찬신");
                customer.setCustomerEmail("test"+i+"@naver.com");
                customer.setCustomerPhone("0101111"+i);
                customer.setCustomerAddress("숭의"+i+"동");
                customer.setCreateTime(createTime);
                customer.setUpdateTime(createTime);

                Payment payment = new Payment();
                payment.setPaymentTitle("테스트제목"+i);
                payment.setPaymentType(OrderType.basic);
                payment.setPaymentBizTo(BizTo.BtoB);
                payment.setPaymentDelYn('N');
                payment.setPaymentStatus(OrderStatus.wait);



                // LocalDateTime으로 현재 시간을 가져옴
// 시간 설정

                payment.setCreateTime(createTime);
                payment.setUpdateTime(createTime);


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
    @Test
    void insertMemo() {
        Optional<User> userOp = userRepository.findById(1L);
        LocalDateTime currentTime = LocalDateTime.now();
        if(userOp.isPresent()) {
            User user = userOp.get();
            Random random = new Random();
            for(int i=0; i<=50; i++){
                // 월은 1부터 12까지의 랜덤한 값 설정
                int month = random.nextInt(12) + 1;
                // 일은 1부터 28까지의 랜덤한 값 설정 (월에 따라서 최대 일수가 달라질 수 있으니 적절히 수정하세요)
                int day = random.nextInt(28) + 1;
                LocalDateTime createTime = LocalDateTime.of(currentTime.getYear(), month, day, currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
                Memo memo = new Memo();
                memo.setMemoContents("메모테스트"+i);
                memo.setMemoDelYn("N");
                memo.setMemoExposeYn("Y");
                memo.setCreateTime(createTime);
                memo.setUpdateTime(createTime);
                memo.setUser(user);


                memoRepository.save(memo);
            }
        }


    }


}


