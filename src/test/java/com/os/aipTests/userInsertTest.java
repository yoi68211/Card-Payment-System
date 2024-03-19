package com.os.aipTests;

import com.os.entity.User;
import com.os.repository.UserRepository;
import com.os.util.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public class userInsertTest {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public userInsertTest(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


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
}
