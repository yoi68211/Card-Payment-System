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




	@Test
	void testList(){
		List<Payment> paymentList = paymentRepository.findAll();

		for(Payment list : paymentList){
			System.out.println(list.toString());
		}
	}

	@Test
	void contextLoads() {
	}

}
