package com.os;

import com.os.payment.util.UserRole;
import com.os.user.User;
import com.os.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class   OsApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	@Test
	void insert(){
		User user = User.builder()
				.email("asd@asd.com")
				.password(passwordEncoder.encode("password"))
				.username("kim")
				.role(UserRole.ADMIN)
				.build();

		userRepository.save(user);
	}


	@Test
	void contextLoads() {
	}

}
