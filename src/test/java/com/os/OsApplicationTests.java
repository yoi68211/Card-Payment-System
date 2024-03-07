package com.os;

import com.os.util.UserRole;
import com.os.entity.User;
import com.os.repository.UserRepository;
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
				.password(passwordEncoder.encode("1234"))
				.username("kim")
				.role(UserRole.ADMIN)
				.build();
		userRepository.save(user);
	}


	@Test
	void contextLoads() {
	}

}
