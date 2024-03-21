package com.os.aipTests;

import com.os.entity.User;
import com.os.repository.UserRepository;
import com.os.util.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootTest
public class userInsertTest {
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
//		userRepository.save(user);


		Assertions.assertThat(user.getUsername()).isNotEqualTo("kim");



	}
}
