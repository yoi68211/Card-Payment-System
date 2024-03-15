package com.os;

import com.os.entity.Customer;
import com.os.entity.Payment;
import com.os.entity.Product;
import com.os.entity.User;
import com.os.repository.CustomerRepository;
import com.os.repository.PaymentRepository;
import com.os.repository.ProductRepository;
import com.os.repository.UserRepository;
import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import com.os.util.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class   OsApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;


	@Test
	void insert(){
		User user = User.builder()
				.email("asd@asd.com")
				.password(passwordEncoder.encode("1234"))
				.username("kim")
				.role(UserRole.ADMIN)
				.build();
		userRepository.save(user);
	}


	@Test
	void paymentInsert(){

		LocalDateTime payday = LocalDateTime.now();

		Payment setpayment = Payment.builder()

				.documentNo("docu-1")
				.title("paymentTitle")
				.bizTo(BizTo.BtoB)
				.type(OrderType.basic)
				.status(OrderStatus.paid)
				.paymentDate("5")
				.cycle("다음달")
				.build();

		paymentRepository.save(setpayment);

		Customer customer = Customer.builder()
//				.payment(setpayment)
				.name("customer1")
				.email("cus@asd.com")
				.phone("01012341234")
				.build();


		customerRepository.save(customer);

		List<Product> productList = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			productList.add(Product.builder()
					.payment(setpayment)
					.price(10000)
					.totalItems(3)
					.build());
		}

		productRepository.saveAll(productList);
	}

//	@Test
//	void testGetPaymentAndProductsByDocumentNo() {

//		Optional<Payment> optionalPayment = paymentRepository.findByDocumentNo("docu-1");
//		if (optionalPayment.isPresent()) {
//			Payment payment = optionalPayment.get();
//			List<Product> products = productRepository.findByPayment(payment);
//
//			// Payment 정보 출력
//			System.out.println("문서 번호가 "+ "docu-1"+ "인 결제 정보:");
//			System.out.println("결제 ID: " + payment.getPaymentId());
//			// 추가 필요한 정보가 있다면 출력
//
//			// Product 정보 출력
//			if (!products.isEmpty()) {
//				System.out.println("문서 번호가 " + "docu-1" + "인 결제에 속한 상품들:");
//				for (Product product : products) {
//					System.out.println("상품 ID: " + product.getProductId());
//					System.out.println("상품 이름: " + product.getName());
//					System.out.println("상품 가격: " +  product.getPrice());
//					// 추가 필요한 정보가 있다면 출력
//				}
//			} else {
//				System.out.println("문서 번호가 " + "docu-1" + "인 결제에 속한 상품이 없습니다.");
//			}
//		} else {
//			System.out.println("문서 번호가 " + "docu-1" + "인 결제를 찾을 수 없습니다.");
//		}

//
//		List<Payment> payments = paymentRepository.findAll();
//
//		for (Payment payment : payments) {
//			List<Customer> customers = payment.getCustomers();
//			for (Customer customer : customers) {
//				String customerName = customer.getName();
//				System.out.println("이름"+customerName);
//			}
//		}
//
//	}

	@Test
	void contextLoads() {
	}

}
